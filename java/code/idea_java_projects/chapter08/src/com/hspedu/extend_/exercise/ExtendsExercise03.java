package com.hspedu.extend_.exercise;

public class ExtendsExercise03 {
    public static void main(String[] args) {

        NotePad notePad = new NotePad("8核", 648, 8, "green");
        PC pc = new PC("8核", 128, 8, "eva");
        System.out.println(notePad.NotePadDetails());
        System.out.println(pc.PCDetails());

    }
}

