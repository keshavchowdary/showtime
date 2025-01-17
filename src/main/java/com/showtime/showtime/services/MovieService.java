package com.showtime.showtime.services;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import com.showtime.showtime.entities.requestEntities.MovieAdd;
import com.showtime.showtime.entities.responseEntities.MovieDetails;
import com.showtime.showtime.entities.responseEntities.MoviesWithActiveBooking;
import com.showtime.showtime.models.Actor;
import com.showtime.showtime.models.Movie;
import com.showtime.showtime.utils.MongoHelper;

@Component
public class MovieService {

    @Autowired
    private MongoHelper mongoHelper;

    public boolean isMovieExistsWithSameTitleDesc(String title, String description) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        query.addCriteria(Criteria.where("description").is(description));
        if (mongoHelper.count(query, Movie.class) > 0) {
            return true;
        }
        return false;
    }

    public void addNewMovie(MovieAdd newMovie) {
        if (isMovieExistsWithSameTitleDesc(newMovie.getTitle(), newMovie.getDescription())) {
            throw new RuntimeException("Movie already exists with same title and description");
        }
        Movie movie = new Movie();
        movie.setTitle(newMovie.getTitle());
        movie.setDescription(newMovie.getDescription());
        movie.setGenre(newMovie.getGenre());
        movie.setReleaseDate(newMovie.getReleaseDate());
        List<ObjectId> actorIds = new ArrayList<>();
        newMovie.getActorIds().forEach(actorId -> {
            Query actorExistQuery = new Query();
            actorExistQuery.addCriteria(Criteria.where("id").is(new ObjectId(actorId.toString())));
            if (mongoHelper.count(actorExistQuery, Actor.class) < 1) {
                throw new RuntimeException("Actor with id is not found");
            }
            actorIds.add(new ObjectId(actorId.toString()));
        });
        movie.setActorIds(actorIds);
        movie.setAvailableToBook(newMovie.isAvailableToBook());
        mongoHelper.save(movie);
    }

    public Movie getMovieById(ObjectId movieId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(movieId));
        Movie movieDetails = (Movie) mongoHelper.findOne(query, Movie.class);
        return movieDetails;
    }

    public void updateMovieBookingStatus(String movieId, boolean activate) {
        Movie movie = getMovieById(new ObjectId(movieId));
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("id").is(new ObjectId(movieId)));
        Update updateQuery = new Update();
        updateQuery.set("isAvailableToBook", activate);
        mongoHelper.updateOne(findQuery, updateQuery, Movie.class);
    }

    public List<MoviesWithActiveBooking> getMoviesListWithActiveBooking() {
        Query query = new Query();
        query.addCriteria(Criteria.where("isAvailableToBook").is(true));
        query.fields().include("id", "title");
        List<Movie> moviesList = (List<Movie>) mongoHelper.find(query, Movie.class);
        return moviesList.stream().map(movie -> {
            MoviesWithActiveBooking moviesWithActiveBooking = new MoviesWithActiveBooking(movie.getId().toString(), movie.getTitle());
            return moviesWithActiveBooking;
        }).collect(Collectors.toList());
    }

    public Object getMovieDetails(String movieId) {
        Aggregation aggregateQuery = Aggregation.newAggregation(
            Aggregation.match(Criteria.where("_id").is(new ObjectId(movieId))),
            Aggregation.project("_id", "title", "genre", "description", "releaseDate", "duration", "actorIds", "isAvailableToBook"),
            Aggregation.lookup("actors", "actorIds", "_id", "actors"),
            Aggregation.project("_id", "title", "description", "genre", "duration", "isAvailableToBook", "actors")
        );
        return mongoHelper.aggregate(aggregateQuery, Movie.class, MovieDetails.class);
    }

}
