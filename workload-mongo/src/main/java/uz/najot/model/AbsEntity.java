package uz.najot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @date: 20 November 2023 $
 * @time: 3:48 PM 22 $
 * @author: Qudratjon Komilov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbsEntity {

    //    @OrderBy
//    @CreationTimestamp
//    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    //    @UpdateTimestamp
//    @Column(nullable = false)
    private Timestamp updatedAt;

}

