# Wotreplay Parser Library

The WOTReplay Parser is a Java library designed to parse and extract data from World of Tanks replay (.wotreplay) files.
This library provides a simple interface to access the key information stored within these replay files, making it easier for developers to analyze and process World of Tanks gameplay replays.

## Features

* Extract basic information (e.g., map, game mode, version, etc.) from replays.
* Access detailed player statistics and battle data.
* Support for multiple replay file versions.
* Lightweight and easy to integrate with other applications.
* Error handling for corrupted or incomplete replay files.

## Getting Started

### Prerequisites
* Java 8 or higher
* Maven (if using Maven as the build tool)

### Installation
#### Maven
To use the WOTReplay Parser library in your project, add the following dependency to your pom.xml:
```
<dependency>
    <groupId>com.example</groupId>
    <artifactId>wotreplay-parser</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Direct Download
Alternatively, you can download the JAR file directly from the [releases page] and include it in your project manually.

## Usage
Here's a simple example of how to use the WOTReplay Parser to extract information from a .wotreplay file:

```
package org.example;
import custom.wotreplayparser.library.WotreplayParser;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WotreplayParser parser = new WotreplayParser();
        String filePath = "C:\\Games\\World_of_Tanks_EU\\replays\\20240821_2220_ussr-R52_Object_261_28_desert.wotreplay";
        try {
            parser.parse(filePath);
            System.out.println(parser.getStringPersonalStats());
            System.out.println(parser.getJsonNodeJacksonPersonalStats());
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
```

## License
This project is licensed under the MIT License
Copyright 2024 DAVID GARDOS

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
