import React, { Component } from "react";

class Counter extends Component {
  state = {
    value: this.props.value
  };

  render() {
    return (
      <React.Fragment>
        <b>{this.props.id}</b>
        <span className="btn btn-primary m-4">{this.formatCount()}</span>
        <button
          onClick={() => {
            this.incrementCounter(2);
          }}
          className="btn btn-secondary btn-sm"
        >
          Increment
        </button>
        <button
          onClick={this.decrementCounter}
          className="btn btn-secondary btn-sm m-4"
        >
          Decrement
        </button>
        <button
          onClick={this.resetCounter}
          className="btn btn-secondary btn-sm m-4"
        >
          Reset
        </button>
        <button
          onClick={() => {
            this.props.onDelete(this.props.id);
          }}
          className="btn btn-secondary btn-sm m-4"
        >
          Delete
        </button>
      </React.Fragment>
    );
  }

  deleteCounter = () => {};

  incrementCounter = i => {
    this.props.onIncrement(this.props.id);
  };

  decrementCounter = () => {
    this.props.onDecrement(this.props.id);
  };

  resetCounter = () => {
    this.setState({ value: 0 });
  };

  formatCount() {
    const { value } = this.props;
    return value === 0 ? <h1>0</h1> : <h1>{value}</h1>;
  }
}

export default Counter;
