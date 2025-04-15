# 🦄 Java JSON Parser

A from-scratch JSON parser built with pure Java. Mostly a learning project.

## 🚀 Features
- Full JSON spec compliance (RFC 8259)
- Zero dependencies
- File I/O support
- Clean object model
- Detailed error reporting
- Human-readable output

## 📦 Installation
```bash
# Clone the repository
git clone https://github.com/HShrigma/JavaJSONParser.git
cd java-json-parser

# Build with Gradle
gradle build
```
## 💻 Basic Usage
```java
// Parse from string
String json = "{\"name\":\"Alice\",\"age\":30,\"active\":true}";
JSONNode node = JSONParser.BuildNode(json);

// Access data
String name = node.AsObject().get("name").AsString(); // "Alice"
```
## 📂 File Operations
```java
// Read JSON file
JSONNode config = JSONFileIO.parseFromFile("input.json");

// Write JSON file
JSONFileIO.writeToFile("output.json", config);
```
## 🧠 Smart Parsing
Handles all JSON types:

```json
{
  "string": "Hello World!",
  "number": 42,
  "float": 3.14159,
  "boolean": true,
  "null": null,
  "array": [1, 2, 3],
  "object": {"nested": "value"}
}
```
