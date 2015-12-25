# Boggle
A command-line java game of boggle vs a computer.
# Structure
The Dictionary is created by using a Trie structure, since it reduces dictionary search time to O(word length) in every case. When parsing through the grid to find words to check against the dictionary, the Trie structure makes this process incredibly quick.

# Future Plans
The next step is to remove the command line and make this a graphical interface only. This could be done in Swing, but building for Android seems like a better idea since nice graphics are more easily managed by using XML layouts as opposed to LayoutManagers in Swing. Also, it would be nice a nice platform to lay on.
