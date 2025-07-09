# Zabezpieczenie łopaty MobLog

## Opis funkcji

Plugin zawiera teraz pełne zabezpieczenie łopaty MobLog przed pozostawaniem na serwerze po wyrzuceniu lub restarcie.

## Jak działa zabezpieczenie

### 1. Zabezpieczenie przy wyrzucaniu

- **Event Handler**: `PlayerDropItemEvent`
- **Działanie**: Gdy gracz wyrzuci łopatę MobLog, zostanie ona natychmiast usunięta z ziemi
- **Komunikat**: Gracz otrzymuje wiadomość "Łopata MobLog zniknęła po wyrzuceniu!"

### 2. Zabezpieczenie po restarcie serwera

- **Metoda**: `cleanupMobLogItemsFromGround()`
- **Wywołanie**: Automatycznie przy starcie serwera (`onEnable`)
- **Działanie**: Skanuje wszystkie światy i usuwa wszystkie łopaty MobLog leżące na ziemi
- **Log**: Zapisuje w konsoli informację o usunięciu każdej znalezionej łopaty

## Implementacja techniczna

### Event Handler dla wyrzucania

```java
@EventHandler
public void onPlayerDropItem(PlayerDropItemEvent event) {
    ItemStack droppedItem = event.getItemDrop().getItemStack();

    // Sprawdza czy wyrzucony przedmiot to MobLog
    if (droppedItem != null && droppedItem.hasItemMeta() && droppedItem.getItemMeta().hasDisplayName()
            && droppedItem.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "MobLog")) {

        // Natychmiast usuwa wyrzuconą łopatę MobLog
        event.getItemDrop().remove();
        event.getPlayer().sendMessage(ChatColor.YELLOW + "Łopata MobLog zniknęła po wyrzuceniu!");
    }
}
```

### Metoda czyszcząca po restarcie

```java
private void cleanupMobLogItemsFromGround() {
    for (World world : getServer().getWorlds()) {
        for (Entity entity : world.getEntities()) {
            if (entity instanceof Item) {
                Item itemEntity = (Item) entity;
                ItemStack itemStack = itemEntity.getItemStack();

                if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()
                        && itemStack.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "MobLog")) {
                    // Usuwa łopatę MobLog z ziemi
                    itemEntity.remove();
                    getLogger().info("Removed MobLog item from ground at: " + itemEntity.getLocation());
                }
            }
        }
    }
}
```

## Charakterystyka łopaty MobLog

### Właściwości

- **Typ**: Kamienna łopata (STONE_SHOVEL)
- **Nazwa**: "MobLog" (złoty kolor)
- **Enchantment**: Unbreaking III (niemożliwość zniszczenia)
- **Pickup**: Nie można podnieść po wyrzuceniu
- **Trwałość**: Znika natychmiast po wyrzuceniu
- **Restart**: Automatycznie usuwana z ziemi po restarcie serwera

### Funkcje łopaty

1. **Sprawdzanie spawnu mobów**: Kliknięcie PPM na moba pokazuje info o spawnie
2. **Zaznaczanie terenu**: LPM/PPM na bloki do zaznaczania obszaru
3. **Informacje o nametagach**: Pokazuje kto i kiedy nadał nametag
4. **Tymczasowość**: Nie zostaje na serwerze po wyrzuceniu

## Bezpieczeństwo

- ✅ Łopata znika natychmiast po wyrzuceniu
- ✅ Automatyczne czyszczenie po restarcie serwera
- ✅ Nie może zostać podniesiona przez innych graczy
- ✅ Nie zostawia śladów na serwerze
- ✅ Pełne zabezpieczenie przed permanentnym pozostaniem

## Testy

Zalecane testy funkcji:

1. Otrzymanie łopaty przez `/blazekill logitem`
2. Wyrzucenie łopaty (Q) - powinna zniknąć
3. Restart serwera z łopatą na ziemi - powinna zostać usunięta
4. Sprawdzenie logów serwera pod kątem komunikatów o usuwaniu

## Changelog

- **v1.0**: Implementacja pełnego zabezpieczenia łopaty MobLog
- **Dodano**: PlayerDropItemEvent handler
- **Dodano**: cleanupMobLogItemsFromGround() wywoływana przy starcie
- **Poprawiono**: Brak możliwości pozostawania łopaty po restarcie
