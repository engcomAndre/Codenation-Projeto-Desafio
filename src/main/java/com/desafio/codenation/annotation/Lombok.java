package com.desafio.codenation.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Lombok {


}
