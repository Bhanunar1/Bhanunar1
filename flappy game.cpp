#include <iostream>
#include <cstdlib>
#include <conio.h>
#include <windows.h>

using namespace std;

bool gameover;
const int width = 20;
const int height = 10;
int x, y; // bird position
int pipeX, pipeY; // pipe position
int score;

enum eDirection { STOP = 0, LEFT, RIGHT, UP, DOWN };
eDirection dir;

void setup()
{
    gameover = false;
    dir = STOP;
    x = width / 2;
    y = height / 2;
    pipeX = width - 1;
    pipeY = rand() % (height - 3) + 1;
    score = 0;
}

void draw()
{
    system("cls"); // Clear console

    for (int i = 0; i < width + 2; i++)
        cout << "#";
    cout << endl;

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            if (j == 0)
                cout << "#"; // left wall

            if (i == y && j == x)
                cout << "O"; // bird
            else if (i == pipeY && j == pipeX)
                cout << "|"; // pipe
            else
                cout << " ";

            if (j == width - 1)
                cout << "#"; // right wall
        }
        cout << endl;
    }

    for (int i = 0; i < width + 2; i++)
        cout << "#";
    cout << endl;

    cout << "Score:" << score << endl;
}

void input()
{
    if (_kbhit())
    {
        switch (_getch())
        {
        case 'a':
            dir = LEFT;
            break;
        case 'd':
            dir = RIGHT;
            break;
        case 'w':
            dir = UP;
            break;
        case 's':
            dir = DOWN;
            break;
        case 'x':
            gameover = true;
            break;
        }
    }
}

void algorithm()
{
    switch (dir)
    {
    case LEFT:
        x--;
        break;
    case RIGHT:
        x++;
        break;
    case UP:
        y--;
        break;
    case DOWN:
        y++;
        break;
    default:
        break;
    }

    // Pipe movement
    pipeX--;

    // Pipe collision check
    if (x == pipeX && (y < pipeY || y > pipeY + 1))
        gameover = true;

    // Score update
    if (pipeX == x)
    {
        score++;
        pipeX = width - 1;
        pipeY = rand() % (height - 3) + 1;
    }

    // Gameover conditions
    if (x <= 0 || x >= width || y <= 0 || y >= height)
        gameover = true;
}

int main()
{
    setup();

    while (!gameover)
    {
        draw();
        input();
        algorithm();
        Sleep(100); // Adjust speed
    }

    return 0;
}

