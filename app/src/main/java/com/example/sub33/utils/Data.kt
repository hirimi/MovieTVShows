package com.example.sub33.utils

import com.example.sub33.entity.MovieEntity
import com.example.sub33.entity.TvShowsEntity
import com.example.sub33.remote.respose.movie.MovieRemote
import com.example.sub33.remote.respose.tv.TvShowsRemote

object Data {

    fun getMovies(): ArrayList<MovieEntity> {
        ArrayList<MovieEntity>().also {
            it.apply {
                add(
                    MovieEntity(
                        337404,
                        "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                        "Cruella",
                        8.6,
                        "2021-05-26",
                        "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
                    )
                )
                add(
                    MovieEntity(
                        399566,
                        "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                        "Godzilla vs. Kong",
                        8.0,
                        "2021-03-24",
                        "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
                    )
                )
                add(
                    MovieEntity(
                        423108,
                        "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                        "The Conjuring: The Devil Made Me Do It",
                        8.2,
                        "2021-05-25",
                        "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense."
                    )
                )
            }

            return it
        }
    }

    fun getTvShows(): ArrayList<TvShowsEntity> {
        ArrayList<TvShowsEntity>().also {
            it.apply {
                add(
                    TvShowsEntity(
                        1399,
                        "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                        "Game of Thrones?",
                        8.4,
                        "2011-04-17",
                        "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
                    )
                )
                add(
                    TvShowsEntity(
                        1402,
                        "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                        "The Walking Dead",
                        8.1,
                        "2010-10-31",
                        "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
                    )
                )
                add(
                    TvShowsEntity(
                        1416,
                        "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                        "Grey's Anatomy",
                        8.2,
                        "2005-03-27",
                        "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
                    )
                )
            }

            return it
        }
    }

    fun getMoviesRemote(): ArrayList<MovieRemote> {
        ArrayList<MovieRemote>().also {
            it.apply {
                add(
                    MovieRemote(
                        id = 337404,
                        poster = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                        title = "Cruella",
                        rating = 8.6,
                        releaseDate = "2021-05-26",
                        synopsis = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
                    )
                )
                add(
                    MovieRemote(
                        id = 399566,
                        poster = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                        title = "Godzilla vs. Kong",
                        rating = 8.0,
                        releaseDate = "2021-03-24",
                        synopsis = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
                    )
                )
                add(
                    MovieRemote(
                        id = 423108,
                        poster = "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                        title = "The Conjuring: The Devil Made Me Do It",
                        rating = 8.2,
                        releaseDate = "2021-05-25",
                        synopsis = "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense."
                    )
                )
            }

            return it
        }
    }

    fun getTvShowsRemote(): ArrayList<TvShowsRemote> {
        ArrayList<TvShowsRemote>().also {
            it.apply {
                add(
                    TvShowsRemote(
                        id = 1399,
                        poster = "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                        title = "Game of Thrones?",
                        rating = 8.4,
                        releaseDate = "2011-04-17",
                        synopsis = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
                    )
                )
                add(
                    TvShowsRemote(
                        id = 1402,
                        poster = "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                        title = "The Walking Dead",
                        rating = 8.1,
                        releaseDate = "2010-10-31",
                        synopsis = "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way."
                    )
                )
                add(
                    TvShowsRemote(
                        id = 1416,
                        poster = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                        title = "Grey's Anatomy",
                        rating = 8.2,
                        releaseDate = "2005-03-27",
                        synopsis = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
                    )
                )
            }

            return it
        }
    }

}