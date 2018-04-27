package elljes16.woact.no.tictactoe.objects;

import android.support.annotation.NonNull;

public class Player implements Comparable
{
    private String name;
    private int win, loss, tie;

    public Player(){}

    public Player(String name)
    {
        win = 0;
        loss = 0;
        tie = 0;
        this.name = name;
    }

    public void matchWin()
    {
        win++;
    }

    public void matchLoss()
    {
        loss++;
    }

    public void matchTie()
    {
        tie++;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getWin()
    {
        return win;
    }

    public void setWin(int win)
    {
        this.win = win;
    }

    public int getLoss()
    {
        return loss;
    }

    public void setLoss(int loss)
    {
        this.loss = loss;
    }

    public int getTie()
    {
        return tie;
    }

    public void setTie(int tie)
    {
        this.tie = tie;
    }

    @Override
    public int compareTo(@NonNull Object o)
    {
        return ((Player)o).getWin() - this.getWin();
    }
}
