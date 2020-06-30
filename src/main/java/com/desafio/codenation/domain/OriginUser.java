package com.desafio.codenation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class OriginUser implements Serializable {
    public static final Long serialUUID = 1L;

    @EmbeddedId
    private OriginUserPk id = new OriginUserPk();

}
