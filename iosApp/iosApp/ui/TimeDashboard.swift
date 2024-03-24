import SwiftUI

struct TimeDashboard: View {
 
    var body: some View {
        HStack {
            Text("00:00:00")
            Button("", systemImage: "play.fill", action: {})
            Button("", systemImage: "pause.fill", action: {})
        }
    }
}

#Preview {
    TimeDashboard()
}
