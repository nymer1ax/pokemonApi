# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**

Aquí tienes el README actualizado con los cuerpos JSON de las solicitudes que estaban en los `curl`:

---

# Pokémon App Postman Collection

This README provides an overview of the **Pokémon App Postman Collection**, which includes various requests to interact with the Pokémon Battle API. The collection covers the essential operations for starting a battle, taking turns, retrieving player information, and fetching battle details.

## Overview

The Pokémon App API allows you to simulate battles between players, including different actions such as attacking, defending, using items, and switching Pokémon. This collection is structured to help you test these functionalities in a simple and organized manner.

## Collection Structure

### 1. Start Battle
This folder contains the request to initiate a battle between two players.

- **start**: Initiates a battle between two players. Replace the `name` parameter with the desired player's name.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/start/:name`
    - **Body**: N/A

### 2. Next Turn
This folder contains requests for player actions during a battle, split into actions for Player 1 and Player 2.

#### Player 1
- **Attack**: Player 1 attacks the opponent's Pokémon.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "user"
          },
          "action": {
              "action": "ATTACK"
          }
      }
      ```

- **Defend**: Player 1 defends, reducing incoming damage.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "user"
          },
          "action": {
              "action": "DEFEND"
          }
      }
      ```

- **Use item**: Player 1 uses an item, like a potion or attack boost.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "user"
          },
          "action": {
              "action": "USE_ITEM",
              "item": "potion o attack_boost"
          }
      }
      ```

- **Switch Pokémon**: Player 1 switches their active Pokémon.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "user"
          },
          "action": {
              "action": "SWITCH_POKEMON",
              "newActivePokemon": {
                  "id": "base1-1",
                  "name": "Alakazam",
                  "type": "Psychic"
              }
          }
      }
      ```

#### Player 2
- **Attack**: Player 2 attacks the opponent's Pokémon.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "system"
          },
          "action": {
              "action": "ATTACK"
          }
      }
      ```

- **Defend**: Player 2 defends, reducing incoming damage.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "system"
          },
          "action": {
              "action": "DEFEND"
          }
      }
      ```

- **Use item**: Player 2 uses an item, like a potion or attack boost.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "system"
          },
          "action": {
              "action": "USE_ITEM",
              "item": "potion o attack_boost"
          }
      }
      ```

- **Switch Pokémon**: Player 2 switches their active Pokémon.

  **Request**:
    - **Method**: `POST`
    - **URL**: `{{mylocal}}/api/pokemon/battle/next-turn`
    - **Body**:
      ```json
      {
          "player": {
              "name": "system"
          },
          "action": {
              "action": "SWITCH_POKEMON",
              "newActivePokemon": {
                  "id": "id_3cb72c21fdaa",
                  "name": "name_806df72db6b9",
                  "type": "type_50e86d34853a"
              }
          }
      }
      ```

### 3. Player Info
This folder contains requests to retrieve information about each player involved in the battle.

- **Player 1**: Fetches details about Player 1.

  **Request**:
    - **Method**: `GET`
    - **URL**: `{{mylocal}}/api/pokemon/battle/player/:name`
    - **Body**: N/A

- **Player 2**: Fetches details about Player 2.

  **Request**:
    - **Method**: `GET`
    - **URL**: `{{mylocal}}/api/pokemon/battle/player/:name`
    - **Body**: N/A

### 4. Battle Info
This folder contains a request to retrieve the current battle's overall information.

- **Battle**: Fetches the current battle status, including players, scores, and active Pokémon.

  **Request**:
    - **Method**: `GET`
    - **URL**: `{{mylocal}}/api/pokemon/battle`
    - **Body**: N/A

## Variables

### Global Variables
- **`mylocal`**: Represents the local server address where the Pokémon API is hosted. Replace this with your actual server address.

## How to Use

1. **Import the Collection**:
    - Import the provided Postman collection into your Postman application.

2. **Set Up Environment**:
    - Make sure to set the `mylocal` environment variable to your local server address, where the Pokémon API is running.

3. **Start a Battle**:
    - Use the **Start Battle** request to initialize a battle between two players.

4. **Take Turns**:
    - Use the requests in the **Next Turn** folder to simulate actions by both players.

5. **Check Player Info**:
    - Retrieve the status of each player using the **Player Info** requests.

6. **Fetch Battle Status**:
    - Get the current battle status at any time using the **Battle Info** request.

## Notes

- Ensure that your Pokémon API server is running before sending requests.
- Replace any placeholder variables in the requests with actual values where necessary.

This collection allows you to fully test and interact with the Pokémon Battle API, providing a structured approach to simulate and monitor battles between players.

--- 

