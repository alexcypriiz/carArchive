package ru.podlubny.carArchive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AppStatus {
    private int statusCode;
    private String message;

}
