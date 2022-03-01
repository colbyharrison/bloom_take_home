
## Running the Project:
Nothing special here, should work (fingers crossed) out of the box

## Project Limitations:
1. I didn't focus on providing full error support. I only show a toast when there is some sort of error.
2. There is no retry logic
3. Testing is not exhaustive. I put down example unit tests to show what they would look like. I did 
   not include any visual tests.
4. No branching or pr strategy just pushing to main...

## Third Party dependencies:
Retrofit: For communicating with numbersApi

Gson-Converter: Paired with Retrofit for json -> object conversions

Okhttp Logging: Provides logging of Retrofit requests
   
