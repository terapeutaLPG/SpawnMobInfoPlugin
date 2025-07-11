# Funkcja wyświetlania UUID gracza w czacie

## Opis funkcji

Plugin BlazeKillTracker zawiera teraz funkcję umożliwiającą wyświetlanie UUID gracza po kliknięciu jego nicku w czacie. Funkcja jest dostępna tylko dla graczy z odpowiednim uprawnieniem.

## Jak działa funkcja

### Uprawnienie

- **Wymagane uprawnienie**: `blazekilltracker.uidview`
- **Domyślnie**: Tylko operatorzy (`default: op`)
- **Cel**: Ograniczenie dostępu do wrażliwych informacji UUID

### Działanie w czacie

1. **Dla graczy z uprawnieniem**: Nick gracza w czacie jest klikalny i ma specjalne właściwości
2. **Hover (najechanie myszką)**: Pokazuje informacje o graczu
3. **Kliknięcie**: Kopiuje UUID do schowka

### Wygląd informacji

Gdy gracz z uprawnieniem najedzie myszką na nick w czacie, widzi:

```
§6Nick: §fSteve
§6UUID: §ff47ac10b-58cc-4372-a567-0e02b2c3d479
§7Kliknij, aby skopiować UUID
```

## Implementacja techniczna

### Event Handler

```java
@EventHandler
public void onPlayerChat(AsyncPlayerChatEvent event) {
    String message = event.getMessage();
    Player sender = event.getPlayer();

    // Tworzy ulepszoną wiadomość z klikalnym nickiem
    TextComponent enhancedMessage = createEnhancedChatMessage(sender, message);

    // Identyfikuje graczy z uprawnieniem
    List<Player> uuidViewers = new ArrayList<>();
    for (Player recipient : event.getRecipients()) {
        if (recipient.hasPermission("blazekilltracker.uidview")) {
            uuidViewers.add(recipient);
        }
    }

    // Wysyła ulepszoną wiadomość do graczy z uprawnieniem
    event.getRecipients().removeAll(uuidViewers);
    for (Player viewer : uuidViewers) {
        viewer.spigot().sendMessage(enhancedMessage);
    }
}
```

### Tworzenie ulepszonej wiadomości

```java
private TextComponent createEnhancedChatMessage(Player sender, String message) {
    // Komponent nazwy gracza
    TextComponent playerNameComponent = new TextComponent(sender.getName());
    playerNameComponent.setColor(net.md_5.bungee.api.ChatColor.YELLOW);

    // Tekst hover z informacjami UUID
    TextComponent hoverText = new TextComponent(
        ChatColor.GOLD + "Nick: " + ChatColor.WHITE + sender.getName() + "\n" +
        ChatColor.GOLD + "UUID: " + ChatColor.WHITE + sender.getUniqueId().toString() + "\n" +
        ChatColor.GRAY + "Kliknij, aby skopiować UUID"
    );

    // Ustawienie hover event
    playerNameComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{hoverText}));

    // Ustawienie click event do kopiowania UUID
    playerNameComponent.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, sender.getUniqueId().toString()));

    // Tworzenie pełnej wiadomości
    TextComponent fullMessage = new TextComponent("<");
    fullMessage.addExtra(playerNameComponent);
    fullMessage.addExtra(new TextComponent("> " + message));

    return fullMessage;
}
```

## Konfiguracja uprawnień

### W plugin.yml

```yaml
blazekilltracker.uidview:
  description: Allows viewing player UUID by clicking on chat names
  default: op
```

### Nadawanie uprawnień

```
# Dla konkretnego gracza
/lp user <nick> permission set blazekilltracker.uidview true

# Dla grupy
/lp group <grupa> permission set blazekilltracker.uidview true

# Odebranie uprawnienia
/lp user <nick> permission unset blazekilltracker.uidview
```

## Funkcje komponentów

### HoverEvent

- **Typ**: `SHOW_TEXT`
- **Zawartość**: Nick, UUID, instrukcja kliknięcia
- **Formatowanie**: Kolorowe, czytelne

### ClickEvent

- **Typ**: `COPY_TO_CLIPBOARD`
- **Zawartość**: UUID gracza
- **Działanie**: Kopiuje UUID do schowka systemowego

### TextComponent

- **Kolor nicku**: Żółty (`ChatColor.YELLOW`)
- **Format wiadomości**: `<NickGracza> wiadomość`
- **Kompatybilność**: Spigot/Paper 1.13+

## Bezpieczeństwo i prywatność

### Ograniczenia dostępu

- ✅ Tylko gracze z uprawnieniem `blazekilltracker.uidview`
- ✅ Domyślnie tylko operatorzy
- ✅ Możliwość precyzyjnego zarządzania przez system uprawnień

### Ochrona prywatności

- ✅ UUID widoczny tylko dla uprzywilejowanych użytkowników
- ✅ Brak automatycznego logowania UUID w czacie
- ✅ Brak możliwości masowego zbierania UUID przez zwykłych graczy

### Funkcjonalność

- ✅ Szybkie kopiowanie UUID do schowka
- ✅ Przejrzyste wyświetlanie informacji
- ✅ Nie wpływa na standardowy czat dla graczy bez uprawnień

## Testowanie

### Zalecane testy

1. **Test uprawnień**: Sprawdź czy tylko gracze z uprawnieniem widzą klikalne nicki
2. **Test hover**: Najedź myszką na nick w czacie
3. **Test kopiowania**: Kliknij nick i sprawdź schowek
4. **Test bez uprawnień**: Sprawdź czy normalni gracze widzą standardowy czat

### Przykład użycia

1. Gracz z uprawnieniem `blazekilltracker.uidview` pisze w czacie
2. Inny gracz z tym samym uprawnieniem widzi klijalny nick
3. Po najechaniu myszką: widzi nick i UUID
4. Po kliknięciu: UUID zostaje skopiowany do schowka

## Kompatybilność

- **Minecraft**: 1.13+
- **Serwer**: Spigot/Paper
- **API**: Bungee Chat API (net.md_5.bungee.api.chat)
- **Klient**: Obsługa hover i click events

## Changelog

- **v1.0**: Implementacja funkcji wyświetlania UUID w czacie
- **Dodano**: AsyncPlayerChatEvent handler
- **Dodano**: createEnhancedChatMessage() method
- **Dodano**: Uprawnienie blazekilltracker.uidview
- **Dodano**: HoverEvent i ClickEvent support
