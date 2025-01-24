package com.showtime.showtime.services;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.showtime.showtime.entities.requestEntities.ShowAdd;
import com.showtime.showtime.models.Movie;
import com.showtime.showtime.models.Screen;
import com.showtime.showtime.models.Show;
import com.showtime.showtime.utils.MongoHelper;

@Component
public class ShowsService {

    @Autowired
    private MongoHelper mongoHelper;

    public void addNewShow(ShowAdd showAdd) {
        if (showAdd.getStartTime().after(showAdd.getEndTime())) {
            throw new RuntimeException("Start time cannot be after end time");
        }
        Query findQueryForMovie = new Query();
        findQueryForMovie.addCriteria(Criteria.where("id").is(showAdd.getMovieId()));
        findQueryForMovie.addCriteria(Criteria.where("isAvailableToBook").is(true));
        int movieCount = mongoHelper.count(findQueryForMovie, Movie.class);
        if (movieCount < 1) {
            throw new RuntimeException("Movie does not exist");
        }
        Query findQueryForScreen = new Query();
        findQueryForScreen.addCriteria(Criteria.where("id").is(new ObjectId(showAdd.getScreenId().toString())));
        int screenCount = mongoHelper.count(findQueryForScreen, Screen.class);
        if (screenCount < 1) {
            throw new RuntimeException("Screen does not exist");
        }
        Query findQueryForShow = new Query();
        findQueryForShow.addCriteria(Criteria.where("endTime").gte(showAdd.getStartTime()));
        findQueryForShow.addCriteria(Criteria.where("startTime").lte(showAdd.getEndTime()));
        int showCount = mongoHelper.count(findQueryForShow, Show.class);
        System.out.println("Show count): " + showCount);
        if (showCount > 0) {
            throw new RuntimeException("Show overlaps with existing show");
        }
        Show show = new Show();
        show.setMovieId(showAdd.getMovieId());
        show.setScreenId(showAdd.getScreenId());
        show.setStartTime(showAdd.getStartTime());
        show.setEndTime(showAdd.getEndTime());
        mongoHelper.save(show);
    }
}
