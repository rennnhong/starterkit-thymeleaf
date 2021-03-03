package indi.rennnhong.staterkit.common.web.response;

/**
 * bean of return payload
 */
public class StudentR {
    private Long id;
    private String name;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public static final class StudentRBuilder {
        private Long id;
        private String name;
        private Integer age;

        private StudentRBuilder() {
        }

        public static StudentRBuilder aStudentR() {
            return new StudentRBuilder();
        }

        public StudentRBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public StudentRBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public StudentRBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public StudentR build() {
            StudentR studentR = new StudentR();
            studentR.setId(id);
            studentR.setName(name);
            studentR.setAge(age);
            return studentR;
        }
    }
}


