# Boggle
A command-line java game of boggle vs a computer.
# Structure
The Dictionary is created by using a Trie structure, since it reduces dictionary search time to O(word length) in every case. When parsing through the grid to find words to check against the dictionary, the Trie structure makes this process incredibly quick.

