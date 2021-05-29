# Rosary Guide
This is a simple guide to praying the Rosary, written in Java. It is currently still a work in progress

### Latest Version
[v0.0.1](https://github.com/km3000/rosary-guide/releases/tag/0.0.1)

## To-do (listed by priority)
* Add GUI arrow buttons for navigation
* Add more languages

## How to use the program
The top menu bar allows the user to select an item and start a new Rosary. After each prayer, press either the right arrow key or Enter to move on to the next. Press the  left arrow key or Backspace to go back.

## Build Instructions
This program was made using Eclipse IDE, so it should be fairly simple to compile from there. It also requires the [Apache Commons Lang3 package](https://commons.apache.org/proper/commons-lang/javadocs/api-release/index.html). After creating your JAR file, make sure that the "pics" and "prayers" folders and the icon files are included with it as they would be before compilation with the source files. See the [releases](https://github.com/km3000/rosary-guide/releases) for an idea of how to package the built program.

## Adding Images
To add an image for one of the msyeries, simply add the picture to the corresponding folder. The program will automatically include it as a one of the images it can randomly select. DO NOT include any other files besides images or the src.txt file in the image folders, as the random image selection algorithm will attempt to render them as images and the program will break.

## Licensing
This program is licensed under the MIT license. See the LICENSE file for more details. See the text files included in the ["pics" folder](https://github.com/km3000/rosary-guide/tree/master/pics) for more information about the [origins and licensing](https://github.com/km3000/rosary-guide/blob/master/pics/licensing.md) of the included pictures.
