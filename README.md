# cbz-utilities 
![CI status](https://github.com/danrog303/cbz-utilities/actions/workflows/build.yml/badge.svg)
![Last updated](https://img.shields.io/github/last-commit/danrog303/cbz-utilities)
![MIT](https://img.shields.io/badge/license-MIT-green)
![Gitmoji](https://img.shields.io/badge/gitmoji-%20📝%20🏗️-FFDD67.svg)

> CLI tool for creating comic book .cbz files with specified metadata and cover. 

## ✨ Features
1. Creating cbz files with specified metadata and cover
2. Unpacking data from existing cbz files
3. Reading metadata from existing cbz files

## ℹ️ What is a cbz file?
A cbz *(comic book zip)* file is a zip archive that stores content of comic book or manga. Using the [KindleComicConverter tool](https://github.com/ciromattia/kcc), such CBZ file can be converted to *epub* or *mobi* format - and this will allow you to read the comic on an ebook reader (for example, Kindle). Cbz file typically consists of two parts:
- the ComicInfo.xml file, which contains metadata about the comic (title, author, series name, volume number in the series)
- a folder containing image files (each image file is one page of the comic book)

## 💡 What is the purpose of this program?
There are several scripts on Github that create cbz files. Unfortunately none of the scripts I found support creating a metadata file (ComicInfo.xml). I decided to create my own program which has all features needed to create comic book file.

## 🖥️ How to use?
### 🖨️ Printing help messages
Print help message about the entire application:
```
$ java -jar cbz-utilities.jar --help
```
Print help message about "create" subcommand:
```
$ java -jar cbz-utilities.jar create --help
```

### 🌈 Creating cbz file
```
$ java -jar cbz-utilities.jar create "/home/danrog303/Documents/inputDirectory" "/home/danrog303/output.cbz" --title "Manga name" --writer "John Doe"
```
The input directory is a folder containing image files - each file will be a single page of the comic book. 
The files should be numbered consecutively, so that their order does not get mixed up after conversion into a cbz file. 
The input folder may contain subfolders - they will be included as chapters in the table of contents.
Below you can find an example: two valid input folder layouts:
```
# Flat structure without chapters
/home/danrog303/Documents/
    inputDirectory/
        1.png
        2.png
        3.png
        4.png

# Structure with chapters
/home/danrog303/Documents/
    inputDirectory/
        Chapter 1/
            1.png
            2.png
        Chapter 2/
            1.png
            2.png
```
Options `--title` and `--writer` are optional and can be used to set metadata of comicbook. All possible arguments are listed below:
<table>
   <tr>
     <td>--writer</td> 
     <td>writer of this comic book or manga</td>
  </tr>
  <tr>
     <td>--title</td> 
     <td>title of this comic book or manga</td>
  </tr>
  <tr>
     <td>--series</td> 
     <td>name of the series</td>
  </tr>
  <tr>
     <td>--volume</td> 
     <td>number of volume in the series</td>
  </tr>
  <tr>
     <td>--genre</td> 
     <td>name of the genre (for example comedy or horror)</td>
  </tr>
  <tr>
     <td>--publisher</td> 
     <td>name of the publisher</td>
  </tr>
  <tr>
     <td>--cover</td> 
     <td>path to cover image (png, jpg or jpeg; can be placed outside of input directory)</td>
  </tr>
</table>

### 📦 Unpacking data from existing cbz files
(Works basically like normal zip extractor)
```
$ java -jar cbz-utilities.jar extract "/home/danrog303/comicbook.cbz" "/home/danrog303/output-dir
```

### 📒 Reading metadata from existing cbz files
The following command will display all metadata recognized by the program on the console:
```
$ java -jar cbz-utilities.jar info "/home/danrog303/comicbook.cbz"
```

