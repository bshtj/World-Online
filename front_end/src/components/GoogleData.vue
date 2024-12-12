<script setup>
function JumpToGoogleSetData() {
    window.open("https://datasetsearch.research.google.com/", '_blank'); // 打开新链接
}
</script>
<template>
    <el-card shadow="hover" @click="JumpToGoogleSetData">Google Dataset Search</el-card>
</template>