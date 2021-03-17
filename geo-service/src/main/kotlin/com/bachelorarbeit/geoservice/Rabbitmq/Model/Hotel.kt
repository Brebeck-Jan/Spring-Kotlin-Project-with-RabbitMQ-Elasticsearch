import com.google.gson.annotations.SerializedName

data class Hotel(

		@SerializedName("name") val name: String = "",
		@SerializedName("coord") val coord: Array<Double> = arrayOf(0.0,0.0),
		val tags: ArrayList<String> = arrayListOf<String>()
)
