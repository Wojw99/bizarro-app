package com.example.bizarro.utils

object Strings {
    // * * * * * * * * GENERAL * * * * * * * *
    const val appName = "Bizarro"
    const val unknownError = "Wystąpił niezidentyfikowany problem!"
    const val networkError = "Wystąpił problem z połączeniem!"
    const val success = "Operacja przebiegła pomyślnie!"
    const val successDelete = "Pomyślnie usunięto ogłoszenie."
    const val refresh = "Odśwież"
    const val iconBack = "Przycisk cofnięcia"
    const val accept = "Akceptuj"
    const val confirm = "Zatwierdź"
    const val edit = "Edytuj"
    const val add = "Dodaj"
    const val select = "Wybierz"
    const val error = "Błąd!"
    const val success2 = "Sukces!"
    const val settings = "Ustawienia"
    const val empty = ""
    const val userRecords = "Twoje ogłoszenia"
    const val home = "Home"
    const val errorIncorrectEmailOrPassword = "Nieprawidłowy login lub hasło!"
    const val userNotSignedInError = "Użytkownik nie jest zalogowany!"
    const val emptyFieldsError = "Część z wymaganych pól jest pusta!"
    const val confirmLogin = "Pomyślnie zalogowano."
    const val photo = "Zdjęcie"
    const val edit_profile = "Edytuj profil"
    const val see_opinions = "Zobacz opinie"
    const val welcomeToBizarro = "Witamy w Bizarro!"
    const val lack = "Brak"
    const val firstname = "Imię"
    const val lastname = "Nazwisko"
    const val phoneNumber = "Numer telefonu"
    const val profileDescription = "Opis profilu"
    const val profileEdit = "Edytuj profil"
    const val type = "Typ"
    const val header = "Nagłówek"
    const val startDate = "Data wystawienia"
    const val noCategoryDesc = "Brak opisu kategorii."
    const val noMarkDesc = "Bądź pierwszym, który wystawi opinię!"

    const val emptyUserEmail = "Nie podano adresu e-mail"
    const val emptyUserPhone = "Nie podano numeru telefonu"
    const val emptyUserDescription = "Brak opisu"
    const val emptyUserAddress = "Nie podano adresu"
    const val emptyUserNames = "Nie podano imienia i nazwiska"

    // * * * * * * * * LOGIN/REGISTER * * * * * * * *
    const val username = "Nazwa użytkownika"
    const val password = "Hasło"
    const val passwordRepeat = "Powtórz hasło"
    const val email = "E-mail"
    const val register = "Zarejestruj"
    const val login = "Zaloguj"
    const val returnToLogin = "Powrót do logowania"
    const val goToRegister = "Utwórz konto"
    const val passwordDontRemember = "Zapomniałem hasła"
    const val passwordReset = "Resetuj hasło"
    const val passwordRestInfo = "Wybierz opcję resetu, aby ustawić nowe hasło. Na twój adres email wysłany zostanie kod, umożliwiający nadanie nowego hasła."
    const val passwordCodeInfo = "Wpisz kod otrzymany na maila i nadaj nowe hasło!"
    const val code = "Kod"

    const val passwordNotEqualsError = "Hasła nie są takie same!"
    const val emailIncorrectError = "E-mail nie jest poprawny!"
    const val emailNotFoundError = "Taki email nie istnieje w naszej bazie danych!"

    // * * * * * * * * SEARCH SCREEN * * * * * * * *
    const val recordImage = "Zdjęcie ogłoszenia."
    const val sellPrice = "Sprzedam"
    const val purchasePrice = "Kupię"
    const val swapObject = "Zamienię"
    const val rentalPeriodPrice = "Wypożyczę"
    const val undefined = "Nie podano"
    const val days = "dni"
    const val listIsEmpty = "Brak wyników"
    const val search = "Czego szukasz?"
    const val filter = "Filtruj"

    // * * * * * * * * FILTER SCREEN * * * * * * * *
    const val filterTitle = "Filtruj"
    const val clearFilters = "Wyczyść filtry"
    const val recordType = "Typ ogłoszenia"
    const val price = "Cena"
    const val category = "Kategoria"
    const val province = "Województwo"
    const val city = "Miasto"

    const val sellPriceHeader = "Cena sprzedaży"
    const val rentHeader1 = "Cena wypożyczenia"
    const val buyPriceHeader = "Cena kupna"
    const val swapHeader = "Co chciałbyś w zamian?"
    const val rentHeader2 = "Na ile dni?"

    const val swapHint = "Np. Trek Marlin 4"
    const val rentHint = "Np. 5"
    const val priceSuffix = "zł"

    const val min = "Min"
    const val max = "Max"

    // * * * * * * * * RECORD DETAILS SCREEN * * * * * * * *
    const val defaultUserName = "Użytkownik"
    const val titleSectionSellLabel = "Sprzedam - proponowana cena"
    const val titleSectionPurchaseLabel = "Kupię - proponowana cena"
    const val titleSectionSwapLabel = "Zamienię - poszukiwany model"
    const val titleSectionRentLabel = "Wypożyczę - cena i okres"
    const val description = "Opis"
    const val info = "info"
    const val opinions = "Opinie"
    const val rate = "Oceń"
    const val recordLoadError = "Nie udało się wczytać ogłoszenia!"
    const val address = "Adres"
    const val addToCompareSuccessMessage = "Pomyślnie dodano do porównywanych ogłoszeń!"
    const val removeFromCompareSuccessMessage = "Pomyślnie usunięto z porównywanych ogłoszeń!"

    const val positiveOpinions = "Większość osób ma pozytywną opinię o transakcjach i kontakcie z tym użytkownikiem."
    const val negativeOpinions = "Większość osób ma negatywną lub neutralną opinię o transakcjach i kontakcie z tym użytkownikiem."

    // * * * * * * * * ADD RECORD SCREEN * * * * * * * *
    const val addRecord = "Dodaj ogłoszenie"
    const val editRecord = "Edytuj ogłoszenie"
    const val recordTitle = "Tytuł ogłoszenia"
    const val recordDescription = "Opis"
    const val title = "Tytuł"
    const val street = "Ulica"
    const val number = "Numer domu/mieszkania"

    // * * * * * * ERRORS * * * * * *
    const val notFoundError = "Nie odnaleziono żądanego zasobu!"
    const val internalServerError = "Błąd wewnętrzny serwera!"
    const val error401 = "Użytkownik nie zalogowany!"

    // * * * * * * COMPARE * * * * * *
    const val clear = "Wyczyść"
    const val added = "Dodano"
    const val userOpinion = "Opinie o użytkowniku"
    const val delete = "Usuń"
    const val details = "Szczegóły"
}