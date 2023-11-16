import {create} from "zustand"

const useStore = create(set => ({
    userInfo: null,
    login: (userInfo) => set(state => (state.userInfo = userInfo)),
    logout: () => set(state => (state.userInfo = null)),
    updateSosomon: (monsterId) => set(state => (state.userInfo = {
        ...state.userInfo,
        profileMonsterId: monsterId,
    })),
}))

export default useStore;
