package com.example.koeco_api.module.boardpost.entity;

import com.example.koeco_api.common.Auditable;
import com.example.koeco_api.module.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_board_post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPost extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private BoardCategory category;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "titleKr", nullable = false, length = 500)
    private String titleKr;

    @Column(name = "titleEn", length = 500)
    private String titleEn;

    @Column(name = "titleCn",  length = 500)
    private String titleCn;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_notice", nullable = false)
    private boolean isNotice; // Có phải bài ghim không?

    @Column(name = "is_private", nullable = false)
    private boolean isPrivate; // Bài viết có phải riêng tư không?

    @Column(name = "link1", length = 255)
    private String link1; // Liên kết 1 (nếu có)

    @Column(name = "link2", length = 255)
    private String link2; // Liên kết 2 (nếu có)

    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardAttachment> attachments;
}
