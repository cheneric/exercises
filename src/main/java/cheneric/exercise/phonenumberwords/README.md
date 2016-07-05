# Phone number words

## Intro

This project converts a series of digits (e.g. a phone number) to all letter combinations 
that form a series of valid words using the letters associated with each digit on a telephone 
keypad.  Each digit must be converted to a letter, and each letter must be part of a valid 
word.

Note that the stub implementation of `Dictionary.isWord()` returns `true` for all words 
passed to it.

## Usage

You can build and run the solution using:

```
./gradlew clean run -q -PmainClass=cheneric.exercise.phonenumberwords.WordChainFinder -Pargs=<phone number>
```

