package com.fs.projectboard.service;

import com.fs.projectboard.domain.Hashtag;
import com.fs.projectboard.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    @Transactional(readOnly = true)
    public Set<Hashtag> findHashtagsByNames(Set<String> hashtagNames) {
        return new HashSet<>(hashtagRepository.findByHashtagNameIn(hashtagNames));
    }

    public Set<String> parseHashtagNames(String content) {
        if (content == null) {
            return Set.of();
        }

        Pattern pattern = Pattern.compile("#[\\w가-힣]+"); // 한글, 영문, 숫자, _ 만 가능
        Matcher matcher = pattern.matcher(content.strip()); // 앞뒤 공백 제거
        Set<String> result = new HashSet<>();

        while (matcher.find()) { // 정규표현식에 맞는 문자열이 있으면 true // find() : 정규표현식에 맞는 문자열 찾기 // matches() : 정규표현식에 맞는 문자열이면 true
            result.add(matcher.group().replace("#", "")); // # 제거  // group() : 정규표현식에 맞는 문자열 반환  // group(0) : 정규표현식에 맞는 문자열 전체 반환 // group(1) : 정규표현식에 맞는 문자열 중 첫번째 그룹 반환
        }

        return Set.copyOf(result); // Set.of() 는 불변 객체라 add() 불가능
    }

    public void deleteHashtagWithoutArticles(Long hashtagId) {
        Hashtag hashtag = hashtagRepository.getReferenceById(hashtagId);
        if (hashtag.getArticles().isEmpty()) {
            hashtagRepository.delete(hashtag);
        }
    }
}
