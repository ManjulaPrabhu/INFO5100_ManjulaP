package com.neu.info5100;

public class Tool {
    protected int strength;
    protected char type;

    void setStrength(int inputstrength) {
        strength = inputstrength;
    }
}

class Rock extends Tool {
    Rock(int strength) {
        (strength);
        this.type = 'r';
    }


    public boolean fight(Scissors s) {
        strength = 2 * strength;
        if (strength > s.strength) {
            return true;
        } else {
            return false;
        }setStrength
    }

    public boolean fight(Paper p) {
        strength = strength / 2;
        if (strength > p.strength) {
            return true;
        } else {
            return false;
        }
    }
}

class Paper extends Tool {
    Paper(int strength) {
        setStrength(strength);
        this.type = 'p';
    }


    public boolean fight(Rock r) {
        strength = 2 * strength;
        if (strength > r.strength) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fight(Scissors s) {
        strength = strength / 2;
        if (strength > s.strength) {
            return true;
        } else {
            return false;
        }
    }
}

class Scissors extends Tool {
    Scissors(int strength) {
        setStrength(strength);
        this.type = 's';
    }

    public boolean fight(Paper p) {
        strength = 2 * strength;
        if (strength > p.strength) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fight(Rock r) {
        strength = strength / 2;
        if (strength > r.strength) {
            return true;
        } else {
            return false;
        }

    }

}

class RockPaperScissorsGame {
    public static void main(String args[]) {
        Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Rock r = new Rock(15);

        System.out.println(s.fight(r) + " , " + p.fight(s));
        System.out.println(p.fight(r) + " , " + r.fight(p));
        System.out.println(r.fight(s) + " , " + s.fight(r));
    }
}
