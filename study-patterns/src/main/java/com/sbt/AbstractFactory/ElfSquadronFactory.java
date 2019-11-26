package com.sbt.AbstractFactory;

public class ElfSquadronFactory extends SquadronFactory {
    public Mage createMage() {
        return new ElfMage();
    }
    public Archer createArcher() {
        return new ElfArcher();
    }
    public Warrior createWarrior() {
        return new ElfWarrior();
    }
}
