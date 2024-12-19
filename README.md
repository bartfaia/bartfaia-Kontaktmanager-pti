# THE Névjegykezelő - Webes Applikáció


Ez a projekt a Tokaj-Hegyalja Egyetem belső kereteiben készült, bemutat egy névjegykezelő webes applikációt.
Az alkalmazás Spring Boot v3.3.5 keretrendszerrel, MariaDB adatbázissal és Bootstrap alapú frontenddel lett megvalósítva.
Az applikáció célja egyszerű névjegykezelési feladatok elvégzése és bemutatása REST API-n keresztül.

---

## Funkciók
- Névjegyek listázása
- Új névjegyek hozzáadása
- Meglévő névjegyek frissítése
- Névjegyek törlése
- Alap JUnit egységtesztek a logika ellenőrzéséhez

---

## Használat
1. Követelmények:
   - Java 21+
   - Maven 3.8+
   - MariaDB (vagy más SQL-kompatibilis adatbázis)

2. **Indítás**:
   - Clone-old le a repository-t:
     ```
     git clone https://github.com/bartfaia/Kontaktmanager-pti.git
     ```
   - Állítsd be az adatbázis elérési adatokat az `application.properties` fájlban.
   - Futtasd a Spring Boot alkalmazást:
     ```
     mvn spring-boot:run
     ```

3. Elérés:
   - Az applikáció alapértelmezett URL-je: `http://localhost:8080`

---

## Tesztadatok
Az alkalmazás megfelelően működik, demó adatokat tartalmaz, melyek az adatbázis inicializálásakor automatikusan betöltődnek (néha pár másodperc várakozás szükséges).

---

## Licenc és Hozzájárulás
Ez a projekt bemutatás és oktatási célból készült, valamint nyilvános megosztásra lett szánva. A projektben használt adatbázis hozzáférések jelenleg "üresek".
Kérlek, hogy körültekintően kezeld a tartalmat.

---

Készítette: Bártfai Attila - JB3BMA - Tokaj-Hegyalja Egyetem, @2024
Ez a hozzáférés 2025. február 03. és 09. között törlésre kerül!
