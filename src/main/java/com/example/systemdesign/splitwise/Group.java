package com.example.systemdesign.splitwise;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String groupId;
    private final String name;
    private final List<String> memberIds;

    public Group(String groupId, String name, List<String> memberIds) {
        this.groupId = groupId;
        this.name = name;
        this.memberIds = new ArrayList<>(memberIds);
    }

    public void addMember(String userId) { if (!memberIds.contains(userId)) memberIds.add(userId); }
    public void removeMember(String userId) { memberIds.remove(userId); }

    public String getGroupId() { return groupId; }
    public String getName() { return name; }
    public List<String> getMemberIds() { return memberIds; }
}
