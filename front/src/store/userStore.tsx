import {observable} from "mobx";

const userStore = observable({
  user: null,
  setUser(userInfo: any){
    this.user = userInfo;
  },
  // *getUser(){
  //   try {
  //     const member = yield memberApi.getUser();
  //     this.user = member.data.data;
  //     return member
  //   } catch (err) {
  //     console.log(err);
  //   }
  // },
});

export default userStore;
