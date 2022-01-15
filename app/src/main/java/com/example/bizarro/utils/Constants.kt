package com.example.bizarro.utils

import androidx.compose.runtime.mutableStateOf
import com.example.bizarro.utils.models.BikeCategory
import com.example.bizarro.utils.models.MarkDescription

object Constants {
    const val BASE_URL = "https://bike-app-1.herokuapp.com/"
    // const val BASE_URL = "http://10.0.2.2:3000/api/"
    const val RECORD_DEFAULT_IMG_URL = "https://upload.wikimedia.org/wikipedia/commons/0/04/Bike_icon.png"
    const val USER_DEFAULT_IMG_URL = "https://upload.wikimedia.org/wikipedia/commons/7/70/User_icon_BLACK-01.png"
    const val SHARED_PREFERENCES_KEY  = "bizarro_shared_prefs_key$%!@#_key2323"

    const val TYPE_SELL = "sprzedam"
    const val TYPE_BUY = "kupię"
    const val TYPE_SWAP = "zamienię"
    const val TYPE_RENT = "wypożyczę"

    const val Review_1 = "1"
    const val Review_2 = "2"
    const val Review_3 = "3"
    const val Review_4 = "4"
    const val Review_5 = "5"

    val provinces = listOf(
        "dolnośląskie",
        "kujawsko-pomorskie",
        "lubelskie",
        "lubuskie",
        "łódzkie",
        "małopolskie",
        "mazowieckie",
        "opolskie",
        "podkarpackie",
        "podlaskie",
        "pomorskie",
        "śląskie",
        "świętokrzyskie",
        "warmińsko-mazurskie",
        "wielkopolskie",
        "zachodniopomorskie",
    )

    val categories = listOf(
        BikeCategory(
            name = "Górski",
            description = "Konstrukcja umożliwia poruszanie się w trudnym terenie. Często wybierany jako środek transportu podczas eskapad górskich.",
        ),
        BikeCategory(
            name = "Szosowy",
            description = "Najlepiej spisze się do jazdy po asfalcie. Często wybierany jako środek transportu podczas przejazdów miejskich.",
        ),
        BikeCategory(
            name = "Gravel",
            description = "Rower szosowy z dodatkami w konstrukcji, ułatwiającymi jazdę w terenie. Często wybierany zarówno do przejazdów miejskich, jak i po leśnych i polnych drogach.",
        ),
        BikeCategory(
            name = "Trekkingowy",
            description = "Rower o turystycznej konstrukcji. Często wyposażone w błotniki, bagażnik, jak i dynamo z oświetleniem.",
        ),
        BikeCategory(
            name = "Cross",
            description = "Rower o konstrukcji łączącej w sobie cechy roweru górskiego i szosowego. Często wybierany do uniwersalnej, turystyczno-miejskiej jazdy.",
        ),
        BikeCategory(
            name = "Miejski",
            description = "Rower o konstrukcji umożliwiającej siedzenie w pozycji wyprostowanej. Często zawiera dodatki zwiększające komfort jazdy.",
        ),
        BikeCategory(
            name = "Fatbike",
            description = "Zbudowany na bazie roweru górskiego, posiadający masywne opony o dużej szerokości. Często wybierany do jazdy po śniegu i piasku.",
        ),
        BikeCategory(
            name = "BMX",
            description = "Rower o konstrukcji ułatwiającej wyskoki oraz akrobacje. Często wybierany do jazdy na specjalnie przygotowanych torach.",
        ),
    )

    val markDescriptions = listOf(
        MarkDescription(
            1.0,
            "Przytłaczająca większość osób ma negatywną opinię o transakcjach i kontakcie z tym użytkownikiem."
        ),
        MarkDescription(
            2.0,
            "Większość osób ma negatywną opinię o transakcjach i kontakcie z tym użytkownikiem."
        ),
        MarkDescription(
            3.0,
            "Zdanie na temat transakcji i kontaku z tym użytkownikiem jest podzielone."
        ),
        MarkDescription(
            4.0,
            "Większość osób ma pozytywną opinię o transakcjach i kontakcie z tym użytkownikiem."
        ),
        MarkDescription(
            5.0,
            "Przytłaczająca większość osób ma pozytywną opinię o transakcjach i kontakcie z tym użytkownikiem."
        ),
    )

    val types = listOf(
        TYPE_BUY,
        TYPE_SELL,
        TYPE_SWAP,
        TYPE_RENT,
    )
}