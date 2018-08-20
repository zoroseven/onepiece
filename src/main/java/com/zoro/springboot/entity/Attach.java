package com.zoro.springboot.entity;

public class Attach {
    private Integer id;

    private String fname;

    private String ftype;

    private Integer authorid;

    private Integer created;

    private String fkey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public String getFtype() {
        return ftype;
    }

    public void setFtype(String ftype) {
        this.ftype = ftype == null ? null : ftype.trim();
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getFkey() {
        return fkey;
    }

    public void setFkey(String fkey) {
        this.fkey = fkey == null ? null : fkey.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Attach other = (Attach) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFname() == null ? other.getFname() == null : this.getFname().equals(other.getFname()))
            && (this.getFtype() == null ? other.getFtype() == null : this.getFtype().equals(other.getFtype()))
            && (this.getAuthorid() == null ? other.getAuthorid() == null : this.getAuthorid().equals(other.getAuthorid()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getFkey() == null ? other.getFkey() == null : this.getFkey().equals(other.getFkey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFname() == null) ? 0 : getFname().hashCode());
        result = prime * result + ((getFtype() == null) ? 0 : getFtype().hashCode());
        result = prime * result + ((getAuthorid() == null) ? 0 : getAuthorid().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getFkey() == null) ? 0 : getFkey().hashCode());
        return result;
    }
}