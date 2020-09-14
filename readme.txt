Steganography.

This software is a Java transcription of the Python one that you can find at:
https://calque.pagesperso-orange.fr/langages/python/message.html

This software encode and decode a message included in an image.
Message is encoded as an ASCII string (ie without accents) which is save at a beginning of line.
The first byte is the length of the string (ie string < 255 cars) end the followings the message itself.

The principe is watching in the following graph:

pixel		1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16
perceval	36	37	37	40	41	44	45	50	50	49	48	44	43	42	40	38
even bit 	36	36	36	40	40	44	44	50	50	48	48	44	42	42	40	38
message		0	1	0	0	1	0	1	1	0	1	0	0	1	0	0	1
result    	36	37	36	40	41	44	45	51	50	49	48	44	43	42	40	39

We use the RED channel
For each pixel   , we got the bigger even integer inferior, and we add the message bit.
If the result is even, then the message's bit is 0, if it's odd, the the message's bit is 1.

Syntax:


In the directory :

Codage:

java -jar ./Stenography.jar codage -in ./fileIn -out ./fileOut -msg "Message" -line 5

Decodage:

java -jar ./Stenography.jar decodage -in ./fileIn -line 5
-> Message

