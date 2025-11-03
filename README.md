# JChess

A fully-functional chess game built with [libGDX](https://libgdx.com/) featuring complete chess rule implementation, check/checkmate detection, and an intuitive drag-and-drop interface.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
  - [Project Structure](#project-structure)
  - [Core Components](#core-components)
  - [Game Flow](#game-flow)
- [Technical Stack](#technical-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Building and Running](#building-and-running)
- [Gameplay](#gameplay)
- [Development](#development)
  - [Gradle Tasks](#gradle-tasks)
  - [Configuration](#configuration)
- [Distribution](#distribution)
- [License](#license)

## Overview

JChess is a cross-platform chess application that implements standard chess rules including piece movement validation, check detection, checkmate logic, and turn-based gameplay. Built on the libGDX framework, it provides a responsive desktop gaming experience with clean graphics and smooth interaction.

## Features

- **Complete Chess Rules Implementation**
  - All standard piece movements (Pawn, Knight, Bishop, Rook, Queen, King)
  - Proper attack patterns and movement constraints
  - Turn-based gameplay with automatic turn switching
  
- **Game Logic**
  - Real-time check detection for both players
  - Checkmate and game-over detection
  - Move validation preventing illegal moves
  - Board state cloning for lookahead validation
  
- **User Interface**
  - Drag-and-drop piece movement
  - Visual highlighting of valid moves
  - Attack squares displayed in different colors
  - Clean menu and end-game screens
  
- **Cross-Platform Support**
  - Desktop builds for Windows, macOS, and Linux
  - Platform-specific optimizations available
  - Optional GraalVM native image compilation

## Architecture

### Project Structure

```
jchess/
├── core/                           # Platform-agnostic game logic
│   └── src/main/java/in/abhinavmishra/jchess/
│       ├── pieces/                 # Chess piece implementations
│       │   ├── Bishop.java
│       │   ├── King.java
│       │   ├── Knight.java
│       │   ├── Pawn.java
│       │   ├── PieceColor.java
│       │   ├── Queen.java
│       │   └── Rook.java
│       ├── screens/                # Game screens
│       │   ├── EndScreen.java     # Game over screen
│       │   ├── GameScreen.java    # Main gameplay screen
│       │   └── MenuScreen.java    # Main menu
│       ├── Assets.java            # Asset management
│       ├── Board.java             # Board state and rendering
│       ├── ChessGame.java         # Main game controller
│       ├── Config.java            # Configuration settings
│       ├── InputHandler.java      # User input processing
│       ├── Piece.java             # Abstract piece base class
│       ├── Square.java            # Individual board square
│       └── Utils.java             # Utility functions
├── lwjgl3/                        # Desktop launcher (LWJGL3)
│   └── src/main/java/in/abhinavmishra/jchess/lwjgl3/
│       └── Lwjgl3Launcher.java
├── assets/                        # Game assets
│   ├── pieces/                    # Chess piece textures
│   └── gdx-skins/                 # UI skins
└── build.gradle                   # Build configuration
```

### Core Components

#### 1. **Game Controller** (`ChessGame`)
The main entry point extending libGDX's `Game` class. Manages screen transitions and application lifecycle.

#### 2. **Board Management** (`Board`)
- Maintains an 8x8 grid of `Square` objects
- Handles piece placement and initial setup
- Manages rendering of board and pieces
- Provides move validation through allowed squares calculation
- Implements board cloning for check simulation
- Tracks turn state (WHITE/BLACK)

#### 3. **Piece System** (`Piece` and subclasses)
Abstract base class `Piece` defines:
- Position tracking (row, col, x, y)
- Color (WHITE/BLACK)
- Texture rendering
- Movement rules (via `setAllowedMoves()` and `getAllowedMoves()`)

Each piece type implements specific movement logic:
- **Pawn**: Forward movement, diagonal captures, double-move on first turn
- **Knight**: L-shaped movement, can jump over pieces
- **Bishop**: Diagonal movement until obstruction
- **Rook**: Horizontal/vertical movement until obstruction
- **Queen**: Combined bishop and rook movement
- **King**: One square in any direction

#### 4. **Square** (`Square`)
Represents individual board positions:
- Holds reference to current piece (or null)
- Manages position coordinates
- Handles rendering of square, piece, and valid move indicators
- Implements hit detection for mouse input

#### 5. **Input System** (`InputHandler`)
Extends libGDX's `InputAdapter` to handle:
- `touchDown`: Piece selection on player's turn
- `touchDragged`: Visual piece dragging
- `touchUp`: Move validation and execution, check/checkmate detection

#### 6. **Screens** (`MenuScreen`, `GameScreen`, `EndScreen`)
Implement libGDX's `Screen` interface:
- **MenuScreen**: Entry point with start button
- **GameScreen**: Main game loop, board rendering, input processing
- **EndScreen**: Victory screen with restart option

#### 7. **Utilities** (`Utils`)
Static helper methods for:
- Initial piece placement
- Check detection (`isKingInCheck`)
- Checkmate validation (`canEscapeCheck`)
- Board state queries

### Game Flow

```
Application Start
    ↓
MenuScreen (Main Menu)
    ↓ [User clicks "Start Game"]
GameScreen Initialization
    ↓
Board Creation (8x8 grid)
    ↓
Piece Placement (Standard chess setup)
    ↓
┌─────────────────────────────────┐
│   Main Game Loop (GameScreen)  │
│                                 │
│  1. Render board and pieces    │
│  2. Process user input          │
│     ├─ Select piece (if turn)  │
│     ├─ Drag piece               │
│     └─ Validate and move        │
│  3. Check for check/checkmate   │
│  4. Switch turns                │
└─────────────────────────────────┘
    ↓ [Checkmate detected]
EndScreen (Victory/Defeat)
    ↓ [User clicks "Restart"]
Back to MenuScreen
```

#### Move Validation Flow

```
User attempts move
    ↓
1. Verify piece belongs to current player
    ↓
2. Calculate allowed moves for selected piece
    ↓
3. Check if target square is in allowed moves
    ↓
4. If valid:
   ├─ Update board state
   ├─ Recalculate all piece moves
   ├─ Simulate: Does this expose own King?
   │   └─ If yes: Reject move
   │   └─ If no: Continue
   ├─ Switch turns
   └─ Check opponent for check/checkmate
    ↓
5. If invalid: Return piece to original square
```

#### Check Detection Algorithm

```java
isKingInCheck(Board, Color):
    1. Locate King of specified color
    2. Get all opponent pieces' allowed moves
    3. Check if any allowed move targets King's position
    4. Return true if King is under attack

canEscapeCheck(Board, Color):
    1. For each piece of specified color:
        a. For each allowed move of that piece:
            i.   Clone the board
            ii.  Simulate the move on cloned board
            iii. Check if King still in check
            iv.  If not in check, return true (escape found)
    2. If no escape found, return false (checkmate)
```

## Technical Stack

- **Framework**: libGDX 1.13.1
- **Build Tool**: Gradle 8.x
- **Language**: Java 8
- **Backend**: LWJGL3 (Lightweight Java Game Library)
- **Graphics**: OpenGL ES 2.0 (via ANGLE emulation)
- **Packaging**: Construo 2.0.1 (for platform-specific distributions)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Git (for cloning the repository)

### Building and Running

#### Quick Start

```bash
# Clone the repository
git clone <repository-url>
cd jchess

# Run the game
./gradlew lwjgl3:run
```

#### Platform-Specific Builds

**Windows:**
```bash
gradlew.bat lwjgl3:run
```

**macOS/Linux:**
```bash
./gradlew lwjgl3:run
```

## Gameplay

1. **Starting the Game**
   - Launch the application
   - Click "Start Game" on the main menu

2. **Making Moves**
   - Click and hold a piece (must be your turn)
   - Drag to desired square
   - Valid moves are highlighted in green
   - Attack moves are highlighted in red
   - Release to place piece

3. **Game Rules**
   - White moves first
   - Turns alternate automatically
   - Invalid moves are rejected
   - Game ends on checkmate

4. **Debug Mode** (Developers)
   - Set `Config.DEV = true` to enable debug overlays
   - Press 'U' key to see all piece movements

## Development

### Gradle Tasks

#### Running and Building

```bash
# Run the application
./gradlew lwjgl3:run

# Build runnable JAR
./gradlew lwjgl3:jar

# Build all projects
./gradlew build

# Clean build artifacts
./gradlew clean
```

#### Platform-Specific JAR Builds

Create smaller JAR files optimized for specific platforms:

```bash
# macOS only (~7MB smaller)
./gradlew jarMac

# Linux only (~5MB smaller)
./gradlew jarLinux

# Windows only (~6MB smaller)
./gradlew jarWin
```

Output location: `lwjgl3/build/libs/`

#### IDE Integration

```bash
# Generate IntelliJ IDEA project files
./gradlew idea

# Generate Eclipse project files
./gradlew eclipse

# Clean IDE project files
./gradlew cleanIdea
./gradlew cleanEclipse
```

#### Advanced Gradle Options

```bash
# Use Gradle daemon (faster repeated builds)
./gradlew --daemon lwjgl3:run

# Work offline (use cached dependencies)
./gradlew --offline build

# Force dependency refresh
./gradlew --refresh-dependencies build

# Continue build even if errors occur
./gradlew --continue build
```

### Configuration

**Application Settings** (`lwjgl3/src/main/java/.../Lwjgl3Launcher.java`)
- Window size: 600x600
- VSync enabled
- Non-resizable window
- ANGLE OpenGL emulation for compatibility

**Game Settings** (`core/src/main/java/.../Config.java`)
- `DEV`: Toggle debug mode (default: `false`)

**Build Settings** (`gradle.properties`)
- `gdxVersion`: libGDX version
- `projectVersion`: Application version
- `enableGraalNative`: Enable GraalVM native compilation (default: `false`)

## Distribution

### Construo Platform Packaging

Build native executables for distribution:

```bash
# Build for all platforms
./gradlew construo

# Individual platform builds
./gradlew construoLinuxX64
./gradlew construoMacM1
./gradlew construoMacX64
./gradlew construoWinX64
```

Supported platforms:
- **Linux**: x86_64
- **macOS**: Apple Silicon (M1/M2) and Intel x86_64
- **Windows**: x86_64

### GraalVM Native Image (Optional)

For ahead-of-time compiled binaries:

1. Set `enableGraalNative=true` in `gradle.properties`
2. Run native image build (see `nativeimage.gradle`)

## License

This project is available for educational and personal use. See repository for full license details.

---

**Author**: Abhinav Mishra (@AbhinavMishra32)  
**Framework**: [libGDX](https://libgdx.com/)  
**Version**: 1.0.0
