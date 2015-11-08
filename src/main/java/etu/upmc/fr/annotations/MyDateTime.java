package etu.upmc.fr.annotations;

import org.springframework.format.annotation.DateTimeFormat;

@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
public @interface MyDateTime {
}
