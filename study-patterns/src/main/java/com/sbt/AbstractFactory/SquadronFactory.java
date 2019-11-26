package com.sbt.AbstractFactory;

/**
 * Created by SBT-Klyshov-DA on 02.07.2018.
 */
public abstract class SquadronFactory {
    public abstract Mage createMage();
    public abstract Archer createArcher();
    public abstract Warrior createWarrior();
}

