# 🔍 ŚLEDZENIE ZMIAN BLOKÓW - BlazeKillTracker

## ✅ Co zostało dodane:

### 🛠️ **System zaznaczania terenu (jak WorldEdit)**

- ✅ **Lewy klik łopatą MobLog** - Ustawia pozycję 1
- ✅ **Prawy klik łopatą MobLog** - Ustawia pozycję 2
- ✅ **Komenda /blazekill sprawdzbloki** - Sprawdza zmiany w zaznaczonym obszarze
- ✅ **Automatyczne śledzenie** - Ważne bloki są śledzone automatycznie

### 📊 **Śledzenie ważnych bloków**

- ✅ **TNT, Respawn Anchor** - Eksplozywne przedmioty
- ✅ **Lava, Water** - Płyny
- ✅ **Diamenty, Szmaragdy, Złoto** - Cenne bloki
- ✅ **Skrzynie, Ender Chest** - Pojemniki
- ✅ **Command Block, Redstone** - Mechanizmy
- ✅ **Dispenser, Dropper, Hopper** - Automaty

### 🗂️ **Automatyczne zarządzanie**

- ✅ **Zapis do pliku** - `block_changes.txt`
- ✅ **Automatyczne czyszczenie** - 7 dni (krócej niż inne logi)
- ✅ **Trwałe dane** - Przetrwają restart serwera

---

## 🎮 Jak używać nowej funkcji:

### 1. **Zaznaczanie obszaru**

```
1. Weź łopatę MobLog: /blazekill logitem
2. Lewy klik na blok → Pozycja 1 ustawiona
3. Prawy klik na blok → Pozycja 2 ustawiona
4. Plugin pokazuje: "Zaznaczono X bloków"
```

### 2. **Sprawdzanie zmian**

```
/blazekill sprawdzbloki
→ Plugin sprawdza wszystkie zmiany w zaznaczonym obszarze
→ Pokazuje do 15 najnowszych zmian
→ Format: Gracz AKCJA Blok (X, Y, Z) - Czas
```

### 3. **Automatyczne śledzenie**

```
Gdy gracz:
- Postawi ważny blok → Zapisuje kto, co, gdzie, kiedy
- Zniszczy ważny blok → Zapisuje kto, co, gdzie, kiedy
```

---

## 🎯 Przykłady użycia:

### **Scenariusz 1: Sprawdzenie griefa**

1. Słyszysz eksplozję w bazie
2. Weź łopatę: `/blazekill logitem`
3. Zaznacz obszar wokół zniszczeń (lewy + prawy klik)
4. Sprawdź: `/blazekill sprawdzbloki`
5. Zobacz kto postawił TNT i kiedy!

### **Scenariusz 2: Sprawdzenie kradzieży**

1. Zauważasz brakującą skrzynię
2. Zaznacz obszar gdzie była skrzynia
3. `/blazekill sprawdzbloki`
4. Zobacz kto zniszczył skrzynię i kiedy!

### **Scenariusz 3: Sprawdzenie budowy**

1. Ktoś zbudował coś z drogich bloków
2. Zaznacz strukturę
3. `/blazekill sprawdzbloki`
4. Zobacz kto stawiał diamenty/złoto!

---

## 📋 Wyniki sprawdzania:

### **Format wyników:**

```
=== Zmiany Bloków w Obszarze ===
Znaleziono 3 zmian:

Player123 postawił TNT (100, 64, 200) - 09-07-2025 18:30:15
Player456 zniszczył CHEST (102, 64, 201) - 09-07-2025 18:25:30
Player789 postawił DIAMOND_BLOCK (98, 65, 199) - 09-07-2025 18:20:45
```

### **Kolorowe komunikaty:**

- 🟢 **Zielony** - "postawił" (blok został postawiony)
- 🔴 **Czerwony** - "zniszczył" (blok został zniszczony)
- 🟡 **Żółty** - Nazwa gracza
- 🔵 **Niebieski** - Typ bloku
- ⚪ **Biały** - Współrzędne
- 🔘 **Szary** - Czas

---

## 🎯 Wszystkie komendy (zaktualizowane):

- `/blazekill active` - Włącza alerty o spawn eggs
- `/blazekill deactive` - Wyłącza alerty o spawn eggs
- `/blazekill hist <gracz>` - Historia respawnów gracza
- `/blazekill logitem` - Daje łopatę MobLog (sprawdza spawny + zaznacza teren)
- `/blazekill lastspawn` - Ostatnich 4 graczy którzy zespawnowali moby
- `/blazekill tp <gracz>` - Teleportuje do ostatniego spawnu gracza
- `/blazekill sprawdzbloki` - **NOWE!** Sprawdza zmiany bloków w zaznaczonym obszarze
- `/blazekill help` - Wyświetla pomoc

---

## 🔍 Tab completion (zaktualizowane):

```
/blazekill [TAB] → active, deactive, hist, logitem, lastspawn, tp, sprawdzbloki, help
/blazekill hist [TAB] → Lista graczy online
/blazekill tp [TAB] → Lista graczy online
```

---

## 🔧 Techniczne szczegóły:

### **Śledzenie bloków**

- **Eventy**: `BlockPlaceEvent`, `BlockBreakEvent`
- **Zapis**: `block_changes.txt` (locationKey;playerName|uuid|blockType|action|time|x|y|z|world)
- **Pamięć**: `HashMap<String, BlockChangeInfo>` dla szybkiego dostępu
- **Czyszczenie**: Automatyczne co 24h (wpisy 7+ dni)

### **Zaznaczanie terenu**

- **Event**: `PlayerInteractEvent` z łopatą MobLog
- **Lewy klik**: Ustawia pierwszą pozycję
- **Prawy klik**: Ustawia drugą pozycję (jeśli nie klikasz na entity)
- **Pamięć**: `HashMap<UUID, Location>` dla każdego gracza

### **Sprawdzanie obszaru**

- Przeszukuje wszystkie współrzędne między pos1 i pos2
- Sprawdza HashMap blockChanges dla każdej lokalizacji
- Sortuje wyniki według czasu (najnowsze pierwsze)
- Ogranicza do 15 wyników żeby uniknąć spamu

---

## 📁 **Wszystkie pliki danych (kompletne):**

Plugin automatycznie tworzy i zarządza:

- `plugins/BlazeKillTracker/blaze_kills.txt` - Zabójstwa Blaze (21 dni)
- `plugins/BlazeKillTracker/spawn_history.txt` - Historia respawnów (21 dni)
- `plugins/BlazeKillTracker/mob_spawn_info.txt` - Informacje o spawnie mobów (21 dni)
- `plugins/BlazeKillTracker/mob_nametags.txt` - Informacje o nametagach (21 dni)
- `plugins/BlazeKillTracker/block_changes.txt` - **NOWE!** Zmiany bloków (7 dni)
- `plugins/BlazeKillTracker/alerts_config.txt` - Ustawienia alertów
- `plugins/BlazeKillTracker/config.yml` - Konfiguracja pluginu

---

## 🚨 Komunikaty:

### **Przy zaznaczaniu:**

```
Pozycja 1 ustawiona: 100, 64, 200
```

```
Pozycja 2 ustawiona: 120, 70, 220
Zaznaczono 2541 bloków. Użyj /blazekill sprawdzbloki
```

### **Przy sprawdzaniu:**

```
Musisz najpierw zaznaczyć obszar łopatą MobLog!
Lewy klik - pozycja 1, prawy klik - pozycja 2
```

```
Obie pozycje muszą być w tym samym świecie!
```

```
Nie znaleziono zmian bloków w zaznaczonym obszarze
Obszar: 21x7x21 bloków
```

```
=== Zmiany Bloków w Obszarze ===
Znaleziono 8 zmian:
Player123 postawił TNT (100, 64, 200) - 09-07-2025 18:30:15
... i 3 więcej
```

---

## 📊 Przykłady zastosowań:

### **1. Administracja serwera**

- Sprawdzanie kto griefuje bazę
- Śledzenie kradzieży z skrzyń
- Monitorowanie używania TNT
- Sprawdzanie nielegalnych farm

### **2. Rozwiązywanie konfliktów**

- "Kto zniszczył moją skrzynię?"
- "Kto wylał tu lawę?"
- "Kto postawił ten command block?"
- "Kto zbudował to z moich diamentów?"

### **3. Bezpieczeństwo**

- Monitoring Respawn Anchors w Overworldzie
- Śledzenie rozstawiania TNT
- Sprawdzanie kto buduje z valuable blocks
- Kontrola używania redstone mechanizmów

---

## 📁 Plik do instalacji:

**`blazekilltracker-1.0.jar`** - Finalna wersja ze śledzeniem bloków

Znajdziesz go w: `c:\Users\igorf\Desktop\PLuginymc\SpawnMobInfoPlugin\target\blazekilltracker-1.0.jar`

---

## 🚀 Instalacja:

1. **Skopiuj** plik `blazekilltracker-1.0.jar` do folderu `plugins` na serwerze
2. **Zrestartuj** serwer
3. **Sprawdź** komendą `/plugins` czy plugin się załadował
4. **Testuj** komendą `/blazekill logitem`

---

## 🎮 Testowanie funkcji bloków:

### **Test podstawowy:**

1. `/blazekill logitem` - Weź łopatę
2. Postaw TNT gdzieś
3. Lewy klik łopatą na blok obok TNT
4. Prawy klik łopatą na inny blok (większy obszar)
5. `/blazekill sprawdzbloki` - Powinieneś zobaczyć info o TNT

### **Test po restarcie:**

1. Postaw jakiś ważny blok (diamond block)
2. Zaznacz obszar i sprawdź - powinieneś zobaczyć
3. `/stop` - Zatrzymaj serwer
4. Uruchom serwer ponownie
5. Zaznacz obszar i sprawdź - info powinno nadal być!

### **Test czyszczenia:**

- Po 7 dniach stare wpisy bloków zostaną automatycznie usunięte
- W konsoli zobaczysz: `Cleaned up X old block change records`

---

## 🎉 Autor: jaruso99

**KOMPLETNY SYSTEM MONITOROWANIA BlazeKillTracker!**

- ✅ Śledzenie spawnu mobów (spawn eggs)
- ✅ Śledzenie zabójstw Blaze
- ✅ Kompletne informacje MobLog (spawny + nametagi)
- ✅ **NOWE:** Śledzenie zmian bloków z zaznaczaniem terenu
- ✅ Historia i statystyki graczy
- ✅ Automatyczne zarządzanie danymi (7-21 dni)
- ✅ Teleportacja do ostatnich spawnów
- ✅ Trwały zapis - wszystko przetrwa restart serwera

**Idealny plugin do pełnego monitorowania aktywności na serwerze!** 🛡️

### **Nowe możliwości:**

🔍 **Śledzenie griefu** - Zobacz kto niszczy struktury  
🏗️ **Monitoring budowy** - Sprawdź kto używa cennych bloków  
💣 **Bezpieczeństwo** - Śledź TNT i Respawn Anchors  
📦 **Ochrona skrzyń** - Wykryj kradzieże  
⚙️ **Kontrola mechanizmów** - Monitoruj redstone i command blocki

**Teraz nic się nie ukryje przed administratorami!** 🕵️‍♂️
