package com.desafio.codenation.domain;

import com.desafio.codenation.domain.origin.Origins;
import com.desafio.codenation.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class OriginUserPk implements Serializable {

    private static final Long serialUUID = 1L;

    @ManyToOne
    @JoinColumn(name = "origins_id")
    private Origins origins;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OriginUserPk)) return false;
        OriginUserPk that = (OriginUserPk) o;
        return Objects.equals(getOrigins(), that.getOrigins()) &&
                Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrigins(), getUser());
    }
}
