import com.google.gson.Gson;
import domain.BaseEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Entity
@Table(name = "MOVIE")
public class Movie extends BaseEntity {

    @Column(nullable = false)
    private  String title;

    @Column(nullable = false)
    private  String director;

    @Column(nullable = false)
    private  String type;

    @Column(nullable = false)
    private  int year;

    @Column(nullable = false)
    @Min(1)
    private  String runtime;

    @Column(nullable = false)
    private  String actors;

    @Column(length = 1000, nullable = false)
    private  String plot;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
        Movie tempMovie = setParametersToTemp();
        this.director = tempMovie.director;
        this.type = tempMovie.type;
        this.year = tempMovie.year;
        this.runtime = tempMovie.runtime;
        this.actors = tempMovie.actors;
        this.plot = tempMovie.plot;
    }

    public String getTitle() {
        return title;
    }

    private Movie setParametersToTemp() {
        String response = getResponse()
                .replace("Title", "title")
                .replace("Director", "director")
                .replace("Year", "year")
                .replace("Genre", "type")
                .replace("Runtime", "runtime")
                .replace("Actors", "actors")
                .replace("Plot", "plot");
        return new Gson().fromJson(response, Movie.class);

    }

    private String getResponse() {
        String apiKey = "21eb6951&";
        String apiPath = "http://www.omdbapi.com/?apikey=";
        String url = apiPath + apiKey + "t=" + title.replace(' ', '-');
        return request(url);
    }

    private static String request(String uri) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(uri);
            getRequest.addHeader("accept", "application/json");
            HttpResponse response = httpClient.execute(getRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String result = "";
            String output;
            while ((output = br.readLine()) != null) {
                result += output;
            }
            httpClient.getConnectionManager().shutdown();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public void showInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                ", runtime='" + runtime + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year &&
                Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(type, movie.type) &&
                Objects.equals(runtime, movie.runtime) &&
                Objects.equals(actors, movie.actors) &&
                Objects.equals(plot, movie.plot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, director, type, year, runtime, actors, plot);
    }
}


