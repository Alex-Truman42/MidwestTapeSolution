

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@JsonIgnoreProperties(ignoreUnknown = true)
class TitleListItem {
	private long titleId;
	private String title;
	private String kind;
	private int year;
	private String artKey;
	private String artistName;
	
	//Getters and Setters for above variables
	public long getTitleId() {
		return titleId;
	}

	public void setTitleId(long titleId) {
		this.titleId = titleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getArtKey() {
		return artKey;
	}

	public void setArtKey(String artKey) {
		this.artKey = artKey;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
}


class Main {

	public static void main(String[] args) {

		TitleListItem titleListItemArray[]; // array of list items to be filled
		try {
			String url = "https://patron-api-gateway-dev.hoopladigital.com/core/kinds/7/titles";

			// Prepares the request: fills with headers and sets a request timeout in case of failure
			HttpRequest request = HttpRequest.newBuilder() 
					.uri(new URI(url)).GET().header("ws-api", "2.1").timeout(Duration.ofSeconds(10)).build();

			
			// Gets the response to the above request from the API; response.body() has the meat of what I want
			HttpResponse<String> response = HttpClient.newHttpClient() 
					.send(request, HttpResponse.BodyHandlers.ofString());

			ObjectMapper mapper = new ObjectMapper();
			
			// Generates an array of objects from the returned JSON array
			TitleListItem titleListItem[] = mapper.readValue(response.body(), TitleListItem[].class); 
		} catch (Exception e) {
			System.err.print(e);
		}
	}

}