package com.example.batch_processing;


/**
 * 작성자를 통해 이름과 성을 가진 사용자 레코드를 인스턴스 화 한다.
 * A simple record class to represent a person.
 * @param firstName
 * @param lastName
 */
public record Person(String firstName, String lastName) {

}