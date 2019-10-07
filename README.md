# Case Study: Conway's Game of Life

## Rules of Mob Programming

- One keyboard, one screen
- One driver, many navigators
- Rotate driver every 15 minutes
- Everyone gets time at the keyboard
- Complete each story (plan, code, test) before moving on
- Take breaks when you need to

## Rules of TDD

- Don't write any production code
  unless it is to make a failing unit test pass.

- Don't write any more of a unit test
  than is sufficient to fail (compilation failures are failures).

- Don't write any more production code
  than is sufficient to pass the one failing unit test.

## Rules of Conway's Game of Life

The universe of the Game of Life is,
a two-dimensional orthogonal grid of square cells,
each of which is in one of two possible states,
alive or dead, (or populated and unpopulated, respectively).

Every cell interacts with its eight neighbours,
which are horizontally, vertically, or diagonally adjacent.

At each step in time, the following transitions occur:

- Any live cell with fewer than two live neighbours dies,
  as if by underpopulation.
- Any live cell with two or three live neighbours lives on
  to the next generation.
- Any live cell with more than three live neighbours dies,
  as if by overpopulation.
- Any dead cell with exactly three live neighbours
  becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system.

The first generation is created by applying the above rules
simultaneously to every cell in the seed;
births and deaths occur simultaneously,
and the discrete moment at which this happens
is sometimes called a tick.

Each generation is a pure function of the preceding one.
The rules continue to be applied repeatedly
to create further generations.

# The Task

As a mob, write a command line application
that calculates the next generation
in a Conway's Game of Life simulation.

The program should take a text input from stdin
that looks like the following:

```
Generation 1:
4 8
........
....*...
...**...
........
```

and produce an output in the same format:

```
$ sbt run < generation1.txt

Generation 2:
4 8
........
...**...
...**...
........
```

If the input is badly formatted,
the program should print an appropriate error message
and fail with a non-zero error code.

# References

- http://codingdojo.org/kata/GameOfLife/
- https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Variations
- https://www.jamasoftware.com/blog/mob-programming-jama/
- http://butunclebob.com/ArticleS.UncleBob.TheThreeRulesOfTdd
- https://natureofcode.com/book/chapter-7-cellular-automata/
