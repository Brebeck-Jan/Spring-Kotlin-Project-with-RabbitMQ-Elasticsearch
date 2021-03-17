import com.google.gson.annotations.SerializedName

data class Hotels (

		@SerializedName("name") val name : String,
		@SerializedName("coord") val coord : List<Double>
)