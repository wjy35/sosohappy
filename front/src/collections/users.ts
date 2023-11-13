import firestore from "@react-native-firebase/firestore"

export const userCollection = firestore().collection("users");

interface createUserProps {
    id: string,
    name: string,
}

export function createUser({id, name}: createUserProps) {
    return userCollection.doc(id).set({
      id, name
    });
  };
  
  export async function getUser(id:string) {
    const doc = await userCollection.doc(id).get();
    return doc.data();
  }