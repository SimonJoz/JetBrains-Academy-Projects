# Text files encryption/decryption app
CLI application for encrypting and decrypting text files.

# Description
- Changing application mode:
    
    '-mode <option>' => Set mode to one of options: [dec | enc].
                  
            e.g: -mode enc => encryption mode;
                 -mode dec => decryption mode;
                 
    Note: if arg: '-mode <option>' is not passed, encrytion mode will be set as default.                    

- Program implements two basic algorithms to crypt text:
    - Caesar cipher: 
        
        This algorithm shifts each letter by the specified number (key_value) according to its 
        order in the alphabet.If the end of the alphabet is reached, starts back at the beginning
        (A follows Z). Non-letter characters remain unchanged.
       
       Read more at: https://en.wikipedia.org/wiki/Caesar_cipher
    
    - Unicode cipher:
        
        Unicode algorithm is very similar to shift cipher but instead of alphabet uses 
        ascii table [Dec:32 -127].
        All characters being encrypted including non-letter characters .

- Setting algorithm:
    
    '-alg <option>' =>  Algorithm options: [shift | unicode]:
              
        e.g: -alg unicode => Unicode cipher.
                -alg shift => Caeser cipher.  
    
    Note: if there is no -alg set the shift algorithm will be executed as default.            

- Setting the key: 
    
    '-key <number_of_shifts>'
    
        e.g: -key 5
             -key 3514613463246   
            
    Note: if there is no -key set the program will consider it as key = 0.   
         
- Enter text for enc/dec manually:
    
    '-data <text>'
      
        e.g: -data 'few words separated with space.'
                -data singleWord                
        
    Note: if text contains words separated with space it must be wrapped in single quotes '<text>'.
    
- Load text from an external file:
    
    '-in <file_path>' => Import data from <file.txt>.
                    
        e.g: -in '/User/Name/road_to_treasure.txt'

- Save result of enc/dec to an external file:
    
    '-out <file_path>' => Export data to <file.txt>.
                  
        e.g: -out '/User/Name/protected.txt'
                   
# Examples of usage
            
- Encryption of text 'Welcome to hyperskill!' with the key 5 and Unicode cipher :
        
        java Main -mode enc -key 5 -data 'Welcome to hyperskill!' -alg unicode
        
            output: \jqhtrj%yt%m~ujwxpnqq&
            
- Decryption of text '\jqhtrj%yt%m~ujwxpnqq&' with the key 5 and Unicode cipher :

        java Main -key 5 -alg unicode -data '\jqhtrj%yt%m~ujwxpnqq&' -mode dec
            
            output: Welcome to hyperskill!

- Encryption of text 'Welcome to hyperskill!' with the key 5 and Caesar cipher:

       java Main -key 5 -alg shift -data 'Welcome to hyperskill!'
            
            output: Bjqhtrj yt mdujwxpnqq!

- Decryption of text 'Bjqhtrj yt mdujwxpnqq!' with the key 5 using Caesar cipher:

       java Main -key 5 -alg shift -data 'Bjqhtrj yt mdujwxpnqq!' -mode dec
    
           output: Welcome to hyperskill!

- Command below import text from the file 'road_to_treasure.txt' encrypt the data with the 'key 5' using Unicode cipher,
create a file called protected.txt and save the cipher to it:
     
       java Main -in road_to_treasure.txt -out protected.txt -key 5 -alg unicode
 
 
 # Notes
 
 - If there is no -mode, the program will work in enc mode.
 - If there is no -key, the program will consider that key = 0.
 - If there is no -data, the program will assume that the data is an empty string.
 - If there is no -alg, the program will use shift algorithm.            
 - If -data parameter contains words separated with space it must be wrapped in single quotes '<text>'.