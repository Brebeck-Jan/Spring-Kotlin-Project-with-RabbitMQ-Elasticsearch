import time, json
from elasticsearch import Elasticsearch, helpers
from datetime import datetime

es = Elasticsearch(hosts=["localhost"])

#Mapping to put in for this index
mapping ={
  "mappings": {
    "properties": {
      "location": {
        "type": "geo_shape"
            },
          "name":{"type":"text"},
          "type":{"type":"text"}
    }
  }
}
connected = False
while not connected:
  try:
    # delete existing entries and index ignore 400 asnd 404
    es.delete_by_query(index="test-index",ignore=[400, 404],wait_for_completion=True, body={'size' : 10000,"query": {"match_all": {}}})
    es.indices.delete(index='geo-index', ignore=[400, 404])
    # ignore 400 cause by IndexAlreadyExistsException when creating an index
    es.indices.create(index='geo-index', body=mapping, ignore=400)
  except Exception as e:
    print(e)
    time.sleep(10)
  connected=True


# files should be in data folder
files = ["/MarketingRegions.geojson"
#"/CitysV2.geojson", "/FederalStates.geojson","/Regions.geojson","/countries.geojson"
]
counter = 0
fail_counter=0

f = open("error.txt", "w")
for filepath in files:
  doc_to_insert=[]
  print("./data"+filepath)
  geo = open("./data"+filepath)
  geo = json.load(geo)
  #build entry
  for entry in geo["features"]:
    
    name = entry["properties"]["NAME"]
    
    geometry = entry["geometry"]
    if geometry["type"]=="Polygon":
      polygon_type="polygon"
    else:
      polygon_type="multipolygon"
    entry_for_db ={
    "name": name,
    "location": {
    "type": polygon_type,
    "coordinates": geometry["coordinates"]
	    },
    "type":filepath[1:-8]
    }
  
    counter += 1
    try:
      es.index(index="geo-index",doc_type='_doc',body=entry_for_db)
    except:
      fail_counter += 1
      if name == None:
        name="empty"
      f.write("./data"+filepath+": "+name+"\n")


f.write(str(counter))
f.write(str(fail_counter))
f.close()

es.indices.refresh("geo-index")

print(counter)
print(es.count(index='geo-index'))
es.close()