# RandomWriter

This project explores the idea of using random writing to produce text that is similar to some known piece
of work. Imagine taking a book, such as Tom Sawyer, and determining the probability with which each character
occurs. You’d probably find that spaces are the most common character, followed by the character e, etc. Given
these probabilities, which we will call a Level 0 analysis, you could randomly produce text that, while not resembling
English, would have the property that the characters would likely occur in the same proportions as they do in Tom
Sawyer. For example, here’s what you might produce:



Level 0:  rla bsht eS ststofo hhfosdsdewno oe wee h .mr ae irii ela iad o r te u t mnyto onmalysnce, ifu en c fDwn oee



Now imagine doing a slightly more sophisticated analysis, a Level 1 analysis, that determines the probability with
which each character follows every other character. You would probably discover that h follows t more frequently
than x does, and you would probably discover that a space follows a period more frequently than a comma does. With
this new analysis, you could use the probabilities from Tom Sawyer to randomly pick an initial character and then
repeatedly choose the next character based on the previous character and the probabilities provided by the analysis.
Your new text might look like the following, which looks a bit more like English than the previous example:


Level 1 “Shand tucthiney m?” le ollds mind Theybooue He, he s whit Pereg lenigabo Jodind alllld ashanthe ainofevids
tre lin-p asto oun theanthadomoere


We can generalize these ideas to a Level k analysis that determines the probability with which each character
follows every possible sequence of k characters. For example, a Level 5 analysis of Tom Sawyer would show that r
follows Sawye more frequently than any other character. After a Level k analysis, you’d be able to produce random
text by always choosing the next character based on the previous k characters – which we will call the seed – and
based on the probabilities produced by your analysis.


For relatively small values of k (5-7), the randomly generated text begins to take on many of the characteristics of
the source text. As the value of k increases, the text looks increasingly like English. Here are some more examples:


Level 2 “Yess been.” for gothin, Tome oso; ing, in to weliss of an’te cle - armit. Paper a comeasione, and smomenty,
fropech hinticer, sid, a was Tom, be such tied. He sis tred a youck to themen


Level 4 en themself, Mr. Welshman, but him awoke, the balmy shore. I’ll give him that he couple overy because in
the slated snuffindeed structure’s kind was rath. She said that the wound the door a fever eyes that WITH him.


Level 6 people had eaten, leaving. Come - didn’t stand it better judgment; His hands and bury it again, tramped
herself! She’d never would be. He found her spite of anything the one was a prime feature sunset, and hit upon
that of the forever.


Level 8 look-a-here - I told you before, Joe. I’ve heard a pin drop. The stillness was complete, how-ever, this is awful
crime, beyond the village was sufficient. He would be a good enough to get that night, Tom and Becky.


Level 10 you understanding that they don’t come around in the cave should get the word “beauteous” was overfondled,
and that together” and decided that he might as we used to do - it’s nobby fun. I’ll learn you.”



This program uses a hashmap to create key-value pairs. The keys in this case are the k-length substrings and the values are
arrays of all the characters that appear directly after all instances of that k-length substring in the input string. The program
initially sets up the hashmap by analyzing the input string and then proceeds to predict the next character of the output string by selecting a 
random character from the array of the current seed in the hashmap.
