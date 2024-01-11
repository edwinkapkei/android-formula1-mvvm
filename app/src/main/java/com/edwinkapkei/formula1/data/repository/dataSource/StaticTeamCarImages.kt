package com.edwinkapkei.formula1.data.repository.dataSource

object StaticTeamCarImages {

    fun getTeamCarImages(year: String): Map<String, String> {
        return mapOf(
            "ferrari" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/ferrari-left.png",
            "mercedes" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/mercedes-left.png",
            "red_bull" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/red-bull-left.png",
            "alpine" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/alpine-left.png",
            "haas" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/haas-f1-team-left.png",
            "alfa" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/alfa-romeo-racing-left.png",
            "alphatauri" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/alphatauri-left.png",
            "mclaren" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/mclaren-left.png",
            "aston_martin" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/aston-martin-left.png",
            "williams" to
                    "https://media.formula1.com/content/dam/fom-website/teams/$year/williams-left.png",
        )
    }
}