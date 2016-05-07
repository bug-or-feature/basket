# basket

Write a program that takes a basket of items and outputs its total cost.
 
The basket can contain one or more of the following items: Bananas, Oranges, Apples, Lemons, Peaches

# plan

Build a command line application, with components designed for extensibility
 
1. Create basic app with default config, default basket, no input, simple output
2. Enable config from external file
3. Enable user input
4. Enable enhanced config options 

# howto

build

    ./gradlew build

run

    java -jar build/libs/basket-1.0.0.jar
    
or, with custom config
    
    java -jar build/libs/basket-1.0.0.jar config.xml

where config.xml looks like

    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <basketConfig>
        <products>
            <entry>
                <key>BANANA</key>
                <value>
                    <price>0.35</price>
                    <minCount>1</minCount>
                </value>
            </entry>
            <entry>
                <key>APPLE</key>
                <value>
                    <price>0.20</price>
                    <minCount>1</minCount>
                </value>
            </entry>
            
            ... etc ...
            
        </products>
    </basketConfig>


# quality

[ci](http://jenkins.bugorfeature.net:7070/job/basket/)

[sonarqube](http://sonar.bugorfeature.net:9000/dashboard/index/535)
