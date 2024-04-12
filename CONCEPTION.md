```markdown
# Project conception (needed file for the submission of our project)

## Resources

Our project is larger than 2.5MB, so our resources are hosted on Google Drive.

### Sources of Images

- [Military Theme](https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pinterest.com%2Fpin%2F728949889678189722%2F)
- [Versus Frame](https://www.vectorstock.com/royalty-free-vector/versus-compare-red-vs-blue-battle-conflict-frame-vector-23111140)
- [Clash of Tanks](https://poki.com/en/g/clash-of-tanks)

### Sprites
All sprites are sourced from [The Spriters Resource](https://www.spriters-resource.com/).

### Sources of Sounds

- [Background Music 1](https://www.youtube.com/watch?v=3ExGuHWdXCE)
- [Background Music 2](https://www.youtube.com/watch?v=su6_bKJJk74)
- [Background Music 3](https://www.youtube.com/watch?v=JURtuwD9TsU)
- [Background Music 4](https://www.youtube.com/watch?v=bhJaMfD6ifs)
- [Background Music 5](https://www.youtube.com/watch?v=1ky1-A0Rk9c)
- [Game Over Sound](https://mixkit.co/free-sound-effects/game-over/)
- [Rocket Launch Sound](https://www.youtube.com/watch?v=w2vm1lcQMxk)

## Modifications of the Model

- Added a method `setColor` in `Text.java` to update the displayed color, primarily used for the pause menu to toggle music and sounds.

- Increased the size of the game window for better comfort and a fuller gaming experience.

## Modifications of Conception

### Class Naming
- Renamed the `Unit` class to `ICWarsUnit` for clarity compared to `ICWarsPlayer`.

### Behavior Modifications
- Removed the `nextWaitingPlayers` list as it was unnecessary. Instead, check if there are no more players in the `currentWaitingList`, remove dead players, and place all living players in the `currentWaitingList`.
- Modified behaviors due to changes in maps.
- Restructured attack rules for tanks and RPGs, allowing them to only fire horizontally or vertically for simpler animations.
- Implemented soldiers' ability to attack within their range as per project specifications.
- Modified behavior for tanks and RPG rockets to hit the first unit they interact with, activating friendly fire for more realism.

### Gameplay Modifications
- In player vs. player or player vs. AI mode, players must place their units on the map before starting the game.
- All AI units spawn randomly on the map.
- Equalized starting turns for fairness in the second level.

### Game Machine Modifications
- Modified game machine states for clearer understanding and functionality:
  - `MAIN_MENU`: Initial menu for choosing game modes.
  - `BET_TEAM`: Menu for betting on a team (optional).
  - `CHOOSE_THEME`: Theme selection menu.
  - `INIT`, `SETUP_UNIT`, `CHOOSE_PLAYER`, `START_PLAYER_TURN`, `PLAYER_TURN`, `END_PLAYER_TURN`, `END_TURN`, `HELIKOPTER`, `END`, `GAME_OVER`, `RESULTS`, `PAUSE`: Various game states for gameplay and interaction.

## Packages Organization

### Actors
- Subdivided into `others`, `players`, and `units`:
  - `others`: Houses entities like missiles, explosions, cities, and helicopters.
  - `players`: Includes player-related classes such as `ICWarsPlayer`, `AIPlayer`, and `RealPlayer`.
  - `units`: Contains unit classes like `ICWarsUnit` and `ICWarsAnimatedUnit`.

### Areas
- Categorized into `levels` and other menus:
  - `levels`: Includes level-specific classes such as `Level0` and `Level1`.
  - `other`: Contains menu-related classes like `BetTeam`, `ChooseTheme`, `GameOver`, `MainMenu`, `Pause`, and `Results`.

### GUI
- Contains classes for user interface elements like player GUI, info panel, and actions panel.

### Utils
- Includes `Faction` enum for faction-related functionalities and `SoundsManager` for sound management.

### Handler
- Houses classes related to interaction handling.

### ICWars Class
- Main class not organized within a subpackage.

## Added Classes

### Utils Classes Added
- `SoundsManager`: A comprehensive sound manager for easier management.
- `Faction`: An enum representing factions used throughout the project.

### Areas Classes Added
- Added various menu classes such as `BetTeam`, `ChooseTheme`, `GameOver`, `MainMenu`, `PauseMenu`, and `Results`, extending the `MenuArea` abstract class.

### Units Classes Added
- `ICWarsAnimatedUnits`: Handles animation for units like tanks, RPGs, and cats.
- Added new unit types under the theme "Cats" including `NormalCat`, `Ours`, and `TankCat`.
- Introduced a military unit `RPG` capable of launching rockets.

### Other Classes Added
- `Capture`: Action to capture cities.
- Added several decorative elements and animations including `Missile`, `Boom`, `Meteorite`, and `Helikopter`.
- Introduced `ICWarsAnimatedUnit` for animated units.
- Implemented a celebration dance for winning teams.
- Added new actions like capturing cities.
```
