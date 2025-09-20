// export class User {
//     userId: number;
//     username: string;
//     password: string;
//     role: string;
//     patientId?: number;
//     doctorId?: number;

//     constructor(
//         userId: number,
//         username: string,
//         password: string,
//         role: string,
//         patientId?: number,
//         doctorId?: number
//     ) {
//         this.userId = userId;
//         this.username = username;
//         this.password = password;
//         this.role = role;
//         this.patientId = patientId;
//         this.doctorId = doctorId;
//     }

//     logAttributes(): void {
//         console.log("userId: " + this.userId);
//         console.log("username: " + this.username);
//         console.log("password: " + this.password);
//         console.log("role: " + this.role);
//         console.log("patientId: " + this.patientId);
//         console.log("doctorId: " + this.doctorId);
//     }
// }



export class User {
  constructor(
    public userId: number,
    public username: string,
    public password: string,
    public role: string,
    public doctor?: {
      doctorId: number;
      fullName: string;
      email: string;
      specialty: string;
      yearsOfExperience: number;
      contactNumber: string;
      logAttributes(): void;
    }
  ) {}
}
