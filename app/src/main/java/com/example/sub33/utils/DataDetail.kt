package com.example.sub33.utils

import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.movie.MovieDetailResponse
import com.example.sub33.remote.respose.tv.TvShowsDetailResponse

object DataDetail {
    fun getMoviesDetails(): MovieEntity {
        return MovieEntity(
            337404,
            "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            "Cruella",
            8.6,
            "2021-05-26",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
        )
    }

    fun getTvShowsDetails(): TvShowsEntity {
        return TvShowsEntity(
            1399,
            "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "Game of Thrones?",
            8.4,
            "2011-04-17",
            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
        )
    }

    fun getMoviesDetailsRemote(): MovieDetailResponse {
        return MovieDetailResponse(
            id = 337404,
            poster = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            title = "Cruella",
            rating = 8.6,
            releaseDate = "2021-05-26",
            sinopsis = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
        )
    }

    fun getTvShowsDetailsRemote(): TvShowsDetailResponse {
        return TvShowsDetailResponse(
            id = 1399,
            poster = "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            title = "Game of Thrones?",
            rating = 8.4,
            releaseDate = "2011-04-17",
            synopsis = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
        )
    }
}